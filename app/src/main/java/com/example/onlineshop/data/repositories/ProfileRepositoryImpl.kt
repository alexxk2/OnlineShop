package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.converters.DatabaseConverter
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.domain.models.User
import com.example.onlineshop.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val roomStorage: RoomStorage,
    private val databaseConverter: DatabaseConverter
) : ProfileRepository {

    override suspend fun getUserInfo(): User {
        val resultFromDB = roomStorage.getUserInfo()
        return databaseConverter.mapUserToDomain(resultFromDB)
    }

    override suspend fun clearAllData() {
       roomStorage.clearAllData()
    }
}