package com.example.onlineshop.domain.catalog

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants

class CatalogInteractorImpl : CatalogInteractor {

    override suspend fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun sortProducts(sortVariant: SortVariants): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun filterProducts(filterWords: List<String>): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorite(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorite(product: Product) {
        TODO("Not yet implemented")
    }
}