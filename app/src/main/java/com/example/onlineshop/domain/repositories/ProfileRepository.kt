package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.User

interface ProfileRepository {

    suspend fun getUserInfo(): User
    suspend fun clearAllData()
}