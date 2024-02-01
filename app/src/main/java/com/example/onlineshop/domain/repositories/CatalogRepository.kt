package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.Product

interface CatalogRepository {

    fun getAllProducts(): List<Product>
}