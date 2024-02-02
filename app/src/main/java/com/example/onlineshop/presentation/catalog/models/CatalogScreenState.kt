package com.example.onlineshop.presentation.catalog.models

import com.example.onlineshop.domain.models.Product

sealed interface CatalogScreenState{

    data object Loading: CatalogScreenState
    data object Error: CatalogScreenState
    data class Content(val products: List<Product>): CatalogScreenState
    data object Empty: CatalogScreenState
}