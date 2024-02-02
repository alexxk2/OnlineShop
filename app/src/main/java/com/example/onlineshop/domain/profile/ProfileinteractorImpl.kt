package com.example.onlineshop.domain.profile

import com.example.onlineshop.domain.models.User
import com.example.onlineshop.domain.repositories.FavoriteRepository
import com.example.onlineshop.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileinteractorImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val favoriteRepository: FavoriteRepository
) : ProfileInteractor {

    override suspend fun getUserInfo(): User {
        return profileRepository.getUserInfo()
    }

    override suspend fun getFavoritesNumber(): Int {
       return favoriteRepository.getFavoritesNumber()
    }

    override suspend fun clearAllData() {
        profileRepository.clearAllData()
    }
}