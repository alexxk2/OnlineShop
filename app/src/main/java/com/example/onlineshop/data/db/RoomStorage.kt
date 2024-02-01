package com.example.onlineshop.data.db

import com.example.onlineshop.data.db.dto.ProductEntity
import com.example.onlineshop.data.db.dto.UserDto

interface RoomStorage {
    suspend fun addToFavorite(productEntity: ProductEntity)
    suspend fun removeFromFavorite(productEntity: ProductEntity)
    suspend fun getAllFavorite(): List<ProductEntity>
    suspend fun getFavoritesNumber(): Int
    suspend fun getUserInfo(): UserDto
    suspend fun clearAllData()
    suspend fun saveUserData(userDto: UserDto)
    suspend fun isUseLoggedIn(): Boolean
}