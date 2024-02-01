package com.example.onlineshop.domain.favorite

import com.example.onlineshop.domain.models.Product

interface FavoriteInteractor {

    suspend fun getAllFavorite(): List<Product>
    suspend fun removeFromFavorite(product: Product)

}