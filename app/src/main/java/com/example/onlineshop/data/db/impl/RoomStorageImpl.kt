package com.example.onlineshop.data.db.impl

import com.example.onlineshop.data.db.FavoriteDao
import com.example.onlineshop.data.db.ProfileDao
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.data.db.dto.ProductEntity
import com.example.onlineshop.data.db.dto.UserDto
import javax.inject.Inject

class RoomStorageImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val profileDao: ProfileDao
) : RoomStorage {


    override suspend fun addToFavorite(productEntity: ProductEntity) {
        favoriteDao.addNewItem(productEntity)
    }

    override suspend fun removeFromFavorite(productEntity: ProductEntity) {
        favoriteDao.removeItem(productEntity)
    }

    override suspend fun getAllFavorite(): List<ProductEntity> {
        return favoriteDao.getAllItems()
    }

    override suspend fun getFavoritesNumber(): Int {
        return favoriteDao.getAllItems().size
    }

    override suspend fun getUserInfo(): UserDto {
        return  profileDao.getAllItems().first()
    }

    override suspend fun clearAllData() {
        profileDao.deleteAllItems()
        favoriteDao.deleteAllItems()
    }

    override suspend fun saveUserData(userDto: UserDto) {
        profileDao.addNewItem(userDto)
    }

    override suspend fun isUseLoggedIn(): Boolean {
        return profileDao.getAllItems().isNotEmpty()
    }
}