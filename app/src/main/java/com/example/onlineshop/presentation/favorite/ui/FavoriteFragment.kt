package com.example.onlineshop.presentation.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentFavoriteBinding
import com.example.onlineshop.presentation.catalog.ui.CatalogImagesAdapter
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCarousel()
    }

    private fun setCarousel() {
        val catalogImagesAdapter = CatalogImagesAdapter()
        val listImages = listOf(R.drawable.blue_foam, R.drawable.orange_lotion)

        binding.vpFavorite.adapter = catalogImagesAdapter
        TabLayoutMediator(binding.tlFavorite, binding.vpFavorite) { tab, position ->
           // tab.setIcon(R.drawable.tab_rectangle_background)

            when(position){
                0 -> tab.text = getString(R.string.goods)
                else -> tab.text = getString(R.string.brands)
            }
        }.attach()

        catalogImagesAdapter.submitList(listImages)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}