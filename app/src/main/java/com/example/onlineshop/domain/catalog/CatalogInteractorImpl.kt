package com.example.onlineshop.domain.catalog

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.SortVariants

class CatalogInteractorImpl : CatalogInteractor {

    override fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun sortProducts(sortVariant: SortVariants): List<Product> {
        TODO("Not yet implemented")
    }

    override fun filterProducts(filterWords: List<String>): List<Product> {
        TODO("Not yet implemented")
    }

    override fun addToFavorite(product: Product) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorite(product: Product) {
        TODO("Not yet implemented")
    }
}