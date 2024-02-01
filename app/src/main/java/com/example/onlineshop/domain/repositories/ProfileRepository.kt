package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.User

interface ProfileRepository {

    fun getUserInfo(): User
    fun clearAllData()
}