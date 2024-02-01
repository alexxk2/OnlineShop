package com.example.onlineshop.domain.registration

import com.example.onlineshop.domain.models.User

interface RegistrationInteractor {
    suspend fun saveUserData(user: User)
    suspend fun isUseLoggedIn(): Boolean
}