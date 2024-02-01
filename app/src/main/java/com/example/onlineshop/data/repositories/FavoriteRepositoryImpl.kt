package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.converters.DatabaseConverter
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val roomStorage: RoomStorage,
    private val databaseConverter: DatabaseConverter
) : FavoriteRepository {

    override suspend fun addToFavorite(product: Product) {
        roomStorage.addToFavorite(databaseConverter.mapProductToData(product))
    }

    override suspend fun removeFromFavorite(product: Product) {
        roomStorage.removeFromFavorite(databaseConverter.mapProductToData(product))
    }

    override suspend fun getAllFavorite(): List<Product> {
        val resultFromDB = roomStorage.getAllFavorite()
        return resultFromDB.map {
            databaseConverter.mapProductToDomain(it)
        }
    }

    override suspend fun getFavoritesNumber(): Int {
        return roomStorage.getFavoritesNumber()
    }
}