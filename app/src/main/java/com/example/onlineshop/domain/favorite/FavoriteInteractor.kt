package com.example.onlineshop.domain.favorite

import com.example.onlineshop.domain.models.Product

interface FavoriteInteractor {

    fun getAllFavorite(): List<Product>
    fun removeFromFavorite(product: Product)

}