package com.example.onlineshop.presentation.details.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentDetailsBinding
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.presentation.catalog.ui.CatalogImagesAdapter
import com.example.onlineshop.presentation.details.view_model.DetailsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(PRODUCT, Product::class.java)!!
            } else {
                it.getParcelable(PRODUCT)!!
            }
            viewModel.setProduct(product)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this){
            sendUpdateRequest()
            findNavController().navigateUp()
            //navigateBack()
        }
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


        binding.tvHideIngredients.setOnClickListener {
            binding.tvIngredientsFull.maxLines = 100
        }

        binding.btnBack.setOnClickListener {
            //navigateBack()
            sendUpdateRequest()
            findNavController().navigateUp()
        }

        bind(product)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setCarousel(imagesIds: List<Int>) {
        val catalogImagesAdapter = CatalogImagesAdapter()

        binding.vpCatalogImages.adapter = catalogImagesAdapter
        TabLayoutMediator(binding.tlCatalogImages, binding.vpCatalogImages) { tab, _ ->
            tab.setIcon(R.drawable.circle_background)
        }.attach()

        catalogImagesAdapter.submitList(imagesIds)
    }

    private fun addStat(title:String, value: String) {
        val statsView = StatsView(requireContext())
        statsView.setStatName(title)
        statsView.setStatValue(value)

        binding.llStats.addView(statsView)
        binding.llStats.invalidate()
        binding.llStats.requestLayout()
    }

    private fun bind(product: Product){
        setCarousel(product.images)


        if (product.isFavorite) {
            makeStateFavorite()
        } else {
            makeStateNotFavorite()
        }

        binding.btnDislike.setOnClickListener {
            viewModel.removeFromFavorite()
            makeStateNotFavorite()
        }

        binding.btnLike.setOnClickListener {
            viewModel.addToFavorite()
            makeStateFavorite()
        }

        with(binding){
            tvItemLabel.text = product.title
            tvItemDescription.text = product.subtitle
            tvItemAvailable.text = getString(R.string.available,getRightEndingItems(product.available))

            product.feedback?.let {feedBack->
                ratingBar.visibility = View.VISIBLE
                tvItemRatingReviews.visibility = View.VISIBLE
                ratingBar.rating = feedBack.rating.toFloat()
                tvItemRatingReviews.text = getString(
                    R.string.rating_and_reviews,
                    feedBack.rating.toString(),
                    getRightEndingReviews(feedBack.count)
                )
            }

            val priceWithDiscountText = "${product.price.priceWithDiscount} ${product.price.unit}"
            tvPriceWithDiscount.text = priceWithDiscountText

            val oldPriceText = "${product.price.price} ${product.price.unit}"
            tvOldPrice.text = oldPriceText

            val discountText = "${product.price.discount}%"
            tvDiscount.text = discountText

            tvDescriptionFull.text = product.description

            tvHideDescription.setOnClickListener {
                tvLabelForward.visibility = View.GONE
                tvDescriptionFull.visibility = View.GONE

                tvHideDescription.visibility = View.GONE
                tvShowDescription.visibility = View.VISIBLE
            }

            tvShowDescription.setOnClickListener {
                tvLabelForward.visibility = View.VISIBLE
                tvDescriptionFull.visibility = View.VISIBLE

                tvHideDescription.visibility = View.VISIBLE
                tvShowDescription.visibility = View.GONE
            }

            if (product.info.isNotEmpty()){
                tvStatsTitle.visibility = View.VISIBLE
                product.info.forEach {
                    addStat(it.title,it.value)
                }
            }
            else{
                tvStatsTitle.visibility = View.GONE
            }

            tvIngredientsFull.text = product.ingredients

            tvHideIngredients.setOnClickListener {
                tvIngredientsFull.maxLines = HID_MAX_LINES
                tvShowIngredients.visibility = View.VISIBLE
                tvHideIngredients.visibility = View.GONE
            }

            tvShowIngredients.setOnClickListener {
                tvIngredientsFull.maxLines = OPEN_MAX_LINES
                tvShowIngredients.visibility = View.GONE
                tvHideIngredients.visibility = View.VISIBLE
            }

            tvBtnPrice.text = priceWithDiscountText
            tvBntOldPrice.text = oldPriceText
        }

    }

    private fun makeStateFavorite(){
        binding.btnLike.visibility = View.GONE
        binding.btnDislike.visibility = View.VISIBLE
    }

    private fun makeStateNotFavorite(){
        binding.btnLike.visibility = View.VISIBLE
        binding.btnDislike.visibility = View.GONE
    }


    private fun sendUpdateRequest(){
        val args = Bundle().apply {
            putParcelable(ID_KEY, viewModel.getProduct())
        }
        setFragmentResult(UPDATE_REQUEST, args)
    }

    private fun getRightEndingReviews(number: Int): String {
        val preLastDigit = number % 100 / 10

        if (preLastDigit == 1) {
            return "$number отзывов"
        }

        return when (number % 10) {
            1 -> "$number отзыв"
            2 -> "$number отзыва"
            3 -> "$number отзыва"
            4 -> "$number отзыва"
            else -> "$number отзывов"
        }
    }

    private fun getRightEndingItems(number: Int): String {
        val preLastDigit = number % 100 / 10

        if (preLastDigit == 1) {
            return "$number штук"
        }

        return when (number % 10) {
            1 -> "$number штука"
            2 -> "$number штуки"
            3 -> "$number штуки"
            4 -> "$number штуки"
            else -> "$number штук"
        }
    }

    companion object{
        const val HID_MAX_LINES = 2
        const val OPEN_MAX_LINES = 100
        const val PRODUCT = "product"

        const val ID_KEY = "update_key"
        const val UPDATE_REQUEST = "update_request"

    }
}