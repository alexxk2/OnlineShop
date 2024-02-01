package com.example.onlineshop.domain.profile

import com.example.onlineshop.domain.models.User

interface ProfileInteractor {
    fun getUserInfo(): User
    fun getFavoritesNumber(): Int
    fun clearAllData()
}