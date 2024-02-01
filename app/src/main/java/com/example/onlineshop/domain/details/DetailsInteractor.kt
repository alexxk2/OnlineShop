package com.example.onlineshop.domain.details

import com.example.onlineshop.domain.models.Product

interface DetailsInteractor {

    suspend fun addToFavorite(product: Product)
    suspend fun removeFromFavorite(product: Product)
}