package com.example.onlineshop.domain.details

import com.example.onlineshop.domain.models.Product

interface DetailsInteractor {

    fun addToFavorite(product: Product)
    fun removeFromFavorite(product: Product)
}