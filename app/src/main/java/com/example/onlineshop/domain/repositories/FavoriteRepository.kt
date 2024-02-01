package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.Product

interface FavoriteRepository {

    fun addToFavorite(product: Product)
    fun removeFromFavorite(product: Product)
    fun getAllFavorite(): List<Product>
    fun getFavoritesNumber(): Int
}