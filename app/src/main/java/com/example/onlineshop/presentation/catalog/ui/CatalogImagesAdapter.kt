package com.example.onlineshop.presentation.catalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.databinding.CatalogCarouselImageBinding

class CatalogImagesAdapter(

) : ListAdapter<Int, CatalogImagesAdapter.CatalogItemViewHolder>(

    object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    inner class CatalogItemViewHolder(val binding: CatalogCarouselImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            image: Int,
        ) {
            Glide.with(binding.ivCatalogImage.context)
                .load(image)
                .fitCenter()
                .into(binding.ivCatalogImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogImagesAdapter.CatalogItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CatalogCarouselImageBinding.inflate(inflater,parent,false)
        return CatalogItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CatalogImagesAdapter.CatalogItemViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)
    }
}


