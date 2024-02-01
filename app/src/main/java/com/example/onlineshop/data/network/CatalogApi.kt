package com.example.onlineshop.data.network

import com.example.onlineshop.data.network.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface CatalogApi {

    @GET("/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getAllProducts(): Response<List<ProductDto>>
}