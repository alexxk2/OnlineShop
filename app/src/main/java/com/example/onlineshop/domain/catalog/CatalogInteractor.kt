package com.example.onlineshop.domain.catalog

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants

interface CatalogInteractor {

    suspend fun getAllProducts(): List<Product>
    suspend fun sortProducts(sortVariant: SortVariants): List<Product>
    suspend fun filterProducts(filterWords: List<String> ): List<Product>
    suspend fun addToFavorite(product: Product)
    suspend fun removeFromFavorite(product: Product)
}