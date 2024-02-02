package com.example.onlineshop.domain.details

import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.repositories.FavoriteRepository
import javax.inject.Inject

class DetailsInteractorImpl @Inject constructor(private val favoriteRepository: FavoriteRepository) :
    DetailsInteractor {

    override suspend fun addToFavorite(product: Product) {
        favoriteRepository.addToFavorite(product)
    }

    override suspend fun removeFromFavorite(product: Product) {
        favoriteRepository.removeFromFavorite(product)
    }
}