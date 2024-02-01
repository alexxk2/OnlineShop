package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.converters.DatabaseConverter
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.domain.models.User
import com.example.onlineshop.domain.repositories.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val roomStorage: RoomStorage,
    private val databaseConverter: DatabaseConverter
) : RegistrationRepository {

    override suspend fun saveUserData(user: User) {
        roomStorage.saveUserData(databaseConverter.mapUserToData(user))
    }

    override suspend fun isUseLoggedIn(): Boolean {
        return roomStorage.isUseLoggedIn()
    }
}