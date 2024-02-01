package com.example.onlineshop.domain.catalog

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants

interface CatalogInteractor {

    fun getAllProducts(): List<Product>
    fun sortProducts(sortVariant: SortVariants): List<Product>
    fun filterProducts(filterWords: List<String> ): List<Product>
    fun addToFavorite(product: Product)
    fun removeFromFavorite(product: Product)
}