package com.example.onlineshop.data.network.impl

import com.example.onlineshop.data.network.CatalogApi
import com.example.onlineshop.data.network.NetworkClient
import com.example.onlineshop.data.network.dto.ProductDto
import javax.inject.Inject

class NetworkClientImpl @Inject constructor(private val retrofitService: CatalogApi) :
    NetworkClient {

    override suspend fun getAllProducts(): List<ProductDto> {
        val response = retrofitService.getAllProducts()
        return if (response.isSuccessful) {
            response.body()?.items ?: emptyList()
        } else {
            emptyList()
        }
    }
}