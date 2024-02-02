package com.example.onlineshop.domain.favorite

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteInteractorImpl @Inject constructor(private val favoriteRepository: FavoriteRepository) :
    FavoriteInteractor {

    override suspend fun getAllFavorite(): List<Product> {
        return favoriteRepository.getAllFavorite()
    }

    override suspend fun removeFromFavorite(product: Product) {
        favoriteRepository.removeFromFavorite(product)
    }
}