package com.example.onlineshop.presentation.favorite.models

import com.example.onlineshop.domain.models.Product

sealed interface FavoriteScreenState{

    data object Empty: FavoriteScreenState
    data object Error: FavoriteScreenState
    data object Loading: FavoriteScreenState
    data class Content(val products: List<Product>): FavoriteScreenState
}