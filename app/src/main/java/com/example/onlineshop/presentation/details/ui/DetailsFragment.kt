package com.example.onlineshop.presentation.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentDetailsBinding
import com.example.onlineshop.presentation.catalog.ui.CatalogImagesAdapter
import com.google.android.material.tabs.TabLayoutMediator


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCarousel()
        setRatingBar(3.45f)


        repeat(5) {
            addStat()
        }

        binding.tvHideIngredients.setOnClickListener {
            binding.tvIngredientsFull.maxLines = 100
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setCarousel() {
        val catalogImagesAdapter = CatalogImagesAdapter()
        val listImages = listOf(R.drawable.blue_foam, R.drawable.orange_lotion)

        binding.vpCatalogImages.adapter = catalogImagesAdapter
        TabLayoutMediator(binding.tlCatalogImages, binding.vpCatalogImages) { tab, position ->
            tab.setIcon(R.drawable.circle_background)
        }.attach()

        catalogImagesAdapter.submitList(listImages)
    }

    private fun setRatingBar(rating: Float) {
        binding.ratingBar.rating = rating
    }

    private fun addStat() {
        val statsView = StatsView(requireContext())
        statsView.setStatName("12345")
        statsView.setStatValue("54321")

        /*   val textView = ConstraintLayout.inflate(requireContext(), R.layout.stats_item, binding.root)
           val nameTextView: TextView = textView.findViewById(R.id.tv_stats_name)
           val valueTextView: TextView = textView.findViewById(R.id.tv_stats_value)
           nameTextView.text = "12345678"
           valueTextView.text = "87654321"*/


        binding.llStats.addView(statsView)
        binding.llStats.invalidate()
        binding.llStats.requestLayout()
    }
}