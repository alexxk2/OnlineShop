package com.example.onlineshop.presentation.catalog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.databinding.CatalogItemBinding
import com.example.onlineshop.domain.models.Product
import com.google.android.material.tabs.TabLayoutMediator

class CatalogAdapter(
    private val onItemClickListener: (product: Product) -> Unit,
    private val onFavoriteAddClickListener: (product: Product) -> Unit,
    private val onFavoriteRemoveClickListener: (product: Product) -> Unit

) : ListAdapter<Product, CatalogAdapter.CatalogViewHolder>(DiffCallBack) {


    inner class CatalogViewHolder(val binding: CatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Product,
            onItemClickListener: (product: Product) -> Unit,
            onFavoriteAddClickListener: (product: Product) -> Unit,
            onFavoriteRemoveClickListener: (product: Product) -> Unit
        ) {
            binding.root.setOnClickListener { onItemClickListener(item) }

            if (item.isFavorite){
                binding.btnLike.visibility = View.GONE
                binding.btnDislike.visibility = View.VISIBLE
                binding.btnDislike.setOnClickListener { onFavoriteRemoveClickListener(item) }
            }
            else{
                binding.btnLike.visibility = View.VISIBLE
                binding.btnDislike.visibility = View.GONE
                binding.btnLike.setOnClickListener { onFavoriteAddClickListener(item) }
            }


            val catalogImagesAdapter = CatalogImagesAdapter()
            binding.vpCatalogImages.adapter = catalogImagesAdapter
            TabLayoutMediator(binding.tlCatalogImages, binding.vpCatalogImages) { tab, position ->
                tab.setIcon(R.drawable.circle_background)
            }.attach()
            catalogImagesAdapter.submitList(item.images)

            val oldPriceText = "${item.price.price} ${item.price.unit}"
            binding.tvOldPrice.text = oldPriceText

            val priceWithDiscountText = "${item.price.priceWithDiscount} ${item.price.unit}"
            binding.tvPriceWithDiscount.text = priceWithDiscountText

            val discountText = "${item.price.discount}%"
            binding.tvDiscount.text = discountText

            binding.tvItemName.text = item.title
            binding.tvItemDescription.text = item.subtitle

            if (item.feedback != null){
                binding.llRating.visibility = View.VISIBLE
                binding.tvRating.text = item.feedback.rating.toString()
                val numberOfReviewsText  = "(${item.feedback.count})"
                binding.tvReviews.text = numberOfReviewsText
            }
            else{
                binding.llRating.visibility = View.GONE
            }

        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogAdapter.CatalogViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CatalogItemBinding.inflate(inflater, parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogAdapter.CatalogViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item,
            onItemClickListener,
            onFavoriteAddClickListener,
            onFavoriteRemoveClickListener
        )
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}