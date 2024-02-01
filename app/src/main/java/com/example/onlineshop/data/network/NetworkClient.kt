package com.example.onlineshop.data.network

import com.example.onlineshop.data.network.dto.ProductDto

interface NetworkClient {

    suspend fun getAllProducts(): List<ProductDto>
}