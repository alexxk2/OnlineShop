package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.models.User

interface RegistrationRepository {

    fun saveUserData(user: User)
    fun isUseLoggedIn(): Boolean
}