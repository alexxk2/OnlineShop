package com.example.onlineshop.presentation.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentCatalogBinding
import com.example.onlineshop.presentation.catalog.models.CatalogScreenState
import com.example.onlineshop.presentation.catalog.models.GridSpaceItemDecoration
import com.example.onlineshop.presentation.catalog.view_model.CatalogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var catalogAdapter: CatalogAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        requireActivity().actionBar?.show()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSortingMenu()
        setChipFilter()
        setRecyclerView()

        viewModel.getAllProducts()
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
                }

                1 -> {
                    binding.tvSorting.text = getString(R.string.sorting_price_from_high)
                }

                else -> {
                    binding.tvSorting.text = getString(R.string.sorting_price_from_low)
                }
            }

            listPopupWindow.dismiss()
        }

        binding.llSorting.setOnClickListener {
            listPopupWindow.show()
        }
    }


    private fun setChipFilter(){

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->

            when(group.checkedChipId){
                binding.chipSeeAll.id-> {
                    manageChipCloseIcons(binding.chipSeeAll.id)
                }
                binding.chipFace.id -> {
                    manageChipCloseIcons(binding.chipFace.id)
                }
                binding.chipBody.id -> {
                    manageChipCloseIcons( binding.chipBody.id)
                }
                binding.chipSuntan.id -> {
                    manageChipCloseIcons(binding.chipSuntan.id)
                }
                binding.chipMassage.id -> {
                    manageChipCloseIcons(binding.chipMassage.id)
                }
                else -> {
                    manageChipCloseIcons(binding.chipLabel.id)
                }

            }

        }
    }

    private fun manageChipCloseIcons(position: Int){

        with(binding) {
            when (position) {
                chipSeeAll.id -> {
                    chipSeeAll.isCloseIconVisible = true
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMassage.isCloseIconVisible = false
                    chipLabel.isCloseIconVisible = false

                    chipSeeAll.setOnCloseIconClickListener {

                    }
                }

                chipFace.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = true
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMassage.isCloseIconVisible = false
                    chipLabel.isCloseIconVisible = false

                    chipFace.setOnCloseIconClickListener {

                    }
                }

                chipBody.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = true
                    chipSuntan.isCloseIconVisible = false
                    chipMassage.isCloseIconVisible = false
                    chipLabel.isCloseIconVisible = false

                    chipBody.setOnCloseIconClickListener {

                    }
                }

                chipSuntan.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = true
                    chipMassage.isCloseIconVisible = false
                    chipLabel.isCloseIconVisible = false

                    chipSuntan.setOnCloseIconClickListener {

                    }
                }

                chipMassage.id -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMassage.isCloseIconVisible = true
                    chipLabel.isCloseIconVisible = false

                    chipMassage.setOnCloseIconClickListener {

                    }
                }

                else -> {
                    chipSeeAll.isCloseIconVisible = false
                    chipFace.isCloseIconVisible = false
                    chipBody.isCloseIconVisible = false
                    chipSuntan.isCloseIconVisible = false
                    chipMassage.isCloseIconVisible = false
                    chipLabel.isCloseIconVisible = true

                    chipLabel.setOnCloseIconClickListener {

                    }
                }
            }
        }
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
            onItemClickListener = {},
            onFavoriteAddClickListener = {},
            onFavoriteRemoveClickListener = {}
        )

        binding.rvCatalog.adapter = catalogAdapter
        binding.rvCatalog.setHasFixedSize(true)
        binding.rvCatalog.addItemDecoration(gridItemDecorator)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}