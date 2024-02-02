package com.example.onlineshop.domain.registration

import com.example.onlineshop.domain.models.User
import com.example.onlineshop.domain.repositories.RegistrationRepository
import javax.inject.Inject

class RegistrationInteractorImpl @Inject constructor(private val registrationRepository: RegistrationRepository) :
    RegistrationInteractor {

    override suspend fun saveUserData(user: User) {
        registrationRepository.saveUserData(user)
    }

    override suspend fun isUseLoggedIn(): Boolean {
        return registrationRepository.isUseLoggedIn()
    }
}