package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.converters.NetworkConverter
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.data.network.NetworkClient
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.repositories.CatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val converter: NetworkConverter,
    private val networkClient: NetworkClient,
    private val roomStorage: RoomStorage
) : CatalogRepository {

    override suspend fun getAllProducts(): List<Product> {

        val resultFromApi = networkClient.getAllProducts()
        val usersFavoritesIds = roomStorage.getAllFavorite().map { it.id }

        return resultFromApi.map {
            if (usersFavoritesIds.contains(it.id)) {
                converter.mapProductToDomainFavorite(it)
            } else {
                converter.mapProductToDomain(it)
            }
        }
    }
}