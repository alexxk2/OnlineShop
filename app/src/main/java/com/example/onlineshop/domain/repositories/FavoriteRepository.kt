package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.Product

interface FavoriteRepository {

    suspend fun addToFavorite(product: Product)
    suspend fun removeFromFavorite(product: Product)
    suspend fun getAllFavorite(): List<Product>
    suspend fun getFavoritesNumber(): Int
}