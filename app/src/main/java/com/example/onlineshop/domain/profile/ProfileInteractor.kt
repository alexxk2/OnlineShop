package com.example.onlineshop.domain.profile

import com.example.onlineshop.domain.models.User

interface ProfileInteractor {

    suspend fun getUserInfo(): User
    suspend fun getFavoritesNumber(): Int
    suspend fun clearAllData()
}