package com.example.onlineshop.domain.registration

import com.example.onlineshop.domain.models.User

interface RegistrationInteractor {
    fun saveUserData(user: User)
    fun isUseLoggedIn(): Boolean
}