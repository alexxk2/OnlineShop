package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.User

interface RegistrationRepository {

    suspend fun saveUserData(user: User)
    suspend fun isUseLoggedIn(): Boolean
}