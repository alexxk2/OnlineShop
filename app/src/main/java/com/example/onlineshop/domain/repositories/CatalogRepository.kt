package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.Product

interface CatalogRepository {

    suspend fun getAllProducts(): List<Product>
}