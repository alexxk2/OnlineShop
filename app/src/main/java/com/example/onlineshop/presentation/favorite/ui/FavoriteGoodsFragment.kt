package com.example.onlineshop.presentation.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentFavouriteGoodsBinding
import com.example.onlineshop.presentation.catalog.models.GridSpaceItemDecoration
import com.example.onlineshop.presentation.catalog.ui.CatalogAdapter
import com.example.onlineshop.presentation.favorite.models.FavoriteScreenState
import com.example.onlineshop.presentation.favorite.view_model.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteGoodsFragment : Fragment() {
    private var _binding: FragmentFavouriteGoodsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var catalogAdapter: CatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteGoodsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.getAllFavorites()
        viewModel.favoriteScreenState.observe(viewLifecycleOwner){state->
            manageScreenContent(state)
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
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(it,false)
                findNavController().navigate(action)
            },
            onFavoriteAddClickListener = {

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

    private fun manageScreenContent(state: FavoriteScreenState){

       with(binding){
            when (state) {
                is FavoriteScreenState.Content -> {
                    rvCatalog.visibility =View.VISIBLE
                    progressBar.visibility =View.GONE
                    tvEmpty.visibility =View.GONE
                    catalogAdapter.submitList(state.products)
                    tvError.visibility = View.GONE
                }

                FavoriteScreenState.Empty -> {
                    rvCatalog.visibility =View.GONE
                    progressBar.visibility =View.GONE
                    tvEmpty.visibility =View.VISIBLE
                    tvError.visibility = View.GONE
                }

                FavoriteScreenState.Loading -> {
                    rvCatalog.visibility =View.GONE
                    progressBar.visibility =View.VISIBLE
                    tvEmpty.visibility =View.GONE
                    tvError.visibility = View.GONE
                }

                FavoriteScreenState.Error -> {
                    rvCatalog.visibility =View.GONE
                    progressBar.visibility =View.GONE
                    tvEmpty.visibility =View.GONE
                    tvError.visibility = View.VISIBLE

                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}