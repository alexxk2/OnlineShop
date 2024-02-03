package com.example.onlineshop.presentation.catalog.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentCatalogBinding
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants
import com.example.onlineshop.presentation.catalog.models.CatalogScreenState
import com.example.onlineshop.presentation.catalog.models.GridSpaceItemDecoration
import com.example.onlineshop.presentation.catalog.view_model.CatalogViewModel
import com.example.onlineshop.presentation.details.ui.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var catalogAdapter: CatalogAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        configureStatusBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSortingMenu()
        setChipFilter()
        setRecyclerView()
        setDetailsResultListener()

        if (!viewModel.catalogScreenState.isInitialized){
            viewModel.getAllProducts()
        }

        viewModel.catalogScreenState.observe(viewLifecycleOwner){state->
            manageScreenContent(state)
        }
    }

    private fun setSortingMenu() {
        val listPopupWindow =
            ListPopupWindow(requireContext(), null, androidx.appcompat.R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = binding.llSorting
        val items = listOf(
            getString(R.string.sorting_popular),
            getString(R.string.sorting_price_from_high),
            getString(R.string.sorting_price_from_low)
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.list_popup_window_item, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.background?.setTint(ContextCompat.getColor(requireContext(), R.color.white))
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            when (position) {
                0 -> {
                    binding.tvSorting.text = getString(R.string.sorting_popular)
                    viewModel.sortProducts(SortVariants.Popular)
                }

                1 -> {
                    binding.tvSorting.text = getString(R.string.sorting_price_from_high)
                    viewModel.sortProducts(SortVariants.HighToLow)
                }

                else -> {
                    binding.tvSorting.text = getString(R.string.sorting_price_from_low)
                    viewModel.sortProducts(SortVariants.LowToHigh)
                }
            }

            listPopupWindow.dismiss()
        }

        binding.llSorting.setOnClickListener {
            listPopupWindow.show()
        }

    }


    private fun setChipFilter(){

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, _ ->

            when(group.checkedChipId){
                group.getChildAt(0).id-> {
                    manageChipCloseIcons(binding.chipSeeAll.id)
                    viewModel.makeFilterDefault()
                    binding.rvCatalog.doOnNextLayout {
                        binding.rvCatalog.smoothScrollToPosition(0)
                    }
                }
                group.getChildAt(1).id -> {
                    manageChipCloseIcons(binding.chipFace.id)
                    viewModel.filterProducts(FACE_TAG)
                }
                group.getChildAt(2).id -> {
                    manageChipCloseIcons( binding.chipBody.id)
                    viewModel.filterProducts(BODY_TAG)
                }
                group.getChildAt(3).id -> {
                    manageChipCloseIcons(binding.chipSuntan.id)
                    viewModel.filterProducts(SUNTAN_TAG)
                }
                else -> {
                    manageChipCloseIcons(binding.chipMask.id)
                    viewModel.filterProducts(MASK_TAG)
                }

            }

        }
    }

    private fun manageChipCloseIcons(id: Int){

        with(binding) {
            when (id) {
                chipSeeAll.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMask.isCloseIconVisible = false
                }

                chipFace.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = true
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMask.isCloseIconVisible = false

                    chipFace.setOnCloseIconClickListener {
                        clearChipGroup()
                    }
                }

                chipBody.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = true
                    chipSuntan.isCloseIconVisible = false
                    chipMask.isCloseIconVisible = false

                    chipBody.setOnCloseIconClickListener {
                        clearChipGroup()
                    }
                }

                chipSuntan.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = true
                    chipMask.isCloseIconVisible = false

                    chipSuntan.setOnCloseIconClickListener {
                        clearChipGroup()
                    }
                }
                else -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMask.isCloseIconVisible = true

                    chipMask.setOnCloseIconClickListener {
                        clearChipGroup()
                    }
                }
            }
        }
    }

    private fun clearChipGroup(){
        binding.chipSeeAll.isChecked = true
        binding.scrollChipFilter.fullScroll(View.FOCUS_LEFT)
    }

    private fun manageScreenContent(state: CatalogScreenState){
        when(state){
            is CatalogScreenState.Content -> {
                binding.rvCatalog.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.tvFilterError.visibility = View.GONE
                catalogAdapter.submitList(state.products)
                binding.btnUpdate.visibility = View.GONE
            }
            CatalogScreenState.Empty -> {
                binding.rvCatalog.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.tvFilterError.visibility = View.VISIBLE
                binding.btnUpdate.visibility = View.GONE
            }
            CatalogScreenState.Error -> {
                binding.rvCatalog.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.tvError.visibility = View.VISIBLE
                binding.tvFilterError.visibility = View.GONE
                binding.btnUpdate.visibility = View.VISIBLE
                binding.btnUpdate.setOnClickListener {
                    viewModel.getAllProducts()
                }
            }
            CatalogScreenState.Loading -> {
                binding.rvCatalog.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
                binding.tvFilterError.visibility = View.GONE
                binding.btnUpdate.visibility = View.GONE
            }
        }
    }

    private fun setRecyclerView(){

        val spaceValue =  resources.getDimension(R.dimen.half_default_margin).toInt()
        val gridItemDecorator = GridSpaceItemDecoration(
            spacing = spaceValue,
            spanCount = 2,
            includeEdge = false
        )
        catalogAdapter = CatalogAdapter(
            onItemClickListener = {
             val action = CatalogFragmentDirections.actionCatalogFragmentToDetailsFragment(it,true)
             findNavController().navigate(action)
            },
            onFavoriteAddClickListener = {
                viewModel.addToFavorite(it)
            },
            onFavoriteRemoveClickListener = {
                viewModel.removeFromFavorite(it)
            }
        )

        binding.rvCatalog.adapter = catalogAdapter
        binding.rvCatalog.setHasFixedSize(true)
        binding.rvCatalog.addItemDecoration(gridItemDecorator)

        val itemAnimator = binding.rvCatalog.itemAnimator
        if (itemAnimator is DefaultItemAnimator){
            itemAnimator.supportsChangeAnimations = false
        }
    }

    private fun setDetailsResultListener(){

        setFragmentResultListener(DetailsFragment.UPDATE_REQUEST){_, bundle->
            val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(DetailsFragment.ID_KEY, Product::class.java)
            } else {
                bundle.getParcelable(DetailsFragment.ID_KEY)
            }
            product?.let {
                viewModel.updateLocalLists(product,product.isFavorite)
            }
        }
    }

    private fun configureStatusBar() {
        val window = requireActivity().window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        requireActivity().actionBar?.show()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = resources.getColor(R.color.white, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val BODY_TAG = "body"
        const val FACE_TAG = "face"
        const val MASK_TAG = "mask"
        const val SUNTAN_TAG = "suntan"
    }
}