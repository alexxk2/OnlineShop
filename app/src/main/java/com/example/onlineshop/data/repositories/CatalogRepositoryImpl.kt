package com.example.onlineshop.data.repositories

import com.example.onlineshop.R
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

        val resultWithImages = resultFromApi.map {
            it.copy(images = provideImages(it.id))
        }

        return resultWithImages.map {
            if (usersFavoritesIds.contains(it.id)) {
                converter.mapProductToDomainFavorite(it)
            } else {
                converter.mapProductToDomain(it)
            }
        }
    }



    private fun provideImages(productId: String): List<Int>{

        val blueFoam = R.drawable.blue_foam
        val orangeLotion = R.drawable.orange_lotion
        val cream = R.drawable.cream
        val paper = R.drawable.paper
        val pinkMask = R.drawable.pink_mask
        val razor = R.drawable.razor

        return when(productId){
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50"->{
                listOf(razor,cream)
            }
            "54a876a5-2205-48ba-9498-cfecff4baa6e"->{
                listOf(blueFoam,orangeLotion)
            }
            "75c84407-52e1-4cce-a73a-ff2d3ac031b3"->{
                listOf(cream,razor)
            }
            "16f88865-ae74-4b7c-9d85-b68334bb97db"->{
                listOf(paper,pinkMask)
            }
            "26f88856-ae74-4b7c-9d85-b68334bb97db"->{
                listOf(orangeLotion,paper)
            }
            "15f88865-ae74-4b7c-9d81-b78334bb97db"->{
                listOf(razor,blueFoam)
            }
            "88f88865-ae74-4b7c-9d81-b78334bb97db"->{
                listOf(pinkMask,paper)
            }
            else->{
                listOf(blueFoam,cream)
            }
        }
    }
}