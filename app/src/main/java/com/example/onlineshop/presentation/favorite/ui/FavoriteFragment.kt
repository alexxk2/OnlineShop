package com.example.onlineshop.presentation.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentFavoriteBinding
import com.example.onlineshop.presentation.catalog.ui.CatalogImagesAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabMediator: TabLayoutMediator


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


        binding.vpFavorite.adapter = FavoriteViewPagerAdapter(childFragmentManager,lifecycle)
        tabMediator = TabLayoutMediator(binding.tlFavorite, binding.vpFavorite) { tab, position ->

            when(position){
                0 -> tab.text = getString(R.string.goods)
                else -> tab.text = getString(R.string.brands)
            }
        }
        tabMediator.attach()

        binding.vpFavorite.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewLifecycleOwner.lifecycleScope.launch{
                    delay(3000L)
                    //вызвать из viewModel показ любимых треков
                }
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator.detach()
        _binding = null
    }
}