package com.example.onlineshop.di

import com.example.onlineshop.domain.catalog.CatalogInteractor
import com.example.onlineshop.domain.catalog.CatalogInteractorImpl
import com.example.onlineshop.domain.details.DetailsInteractor
import com.example.onlineshop.domain.details.DetailsInteractorImpl
import com.example.onlineshop.domain.favorite.FavoriteInteractor
import com.example.onlineshop.domain.favorite.FavoriteInteractorImpl
import com.example.onlineshop.domain.profile.ProfileInteractor
import com.example.onlineshop.domain.profile.ProfileinteractorImpl
import com.example.onlineshop.domain.registration.RegistrationInteractor
import com.example.onlineshop.domain.registration.RegistrationInteractorImpl
import com.example.onlineshop.domain.repositories.CatalogRepository
import com.example.onlineshop.domain.repositories.FavoriteRepository
import com.example.onlineshop.domain.repositories.ProfileRepository
import com.example.onlineshop.domain.repositories.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideCatalogInteractor(
        catalogRepository: CatalogRepository,
        favoriteRepository: FavoriteRepository
    ): CatalogInteractor {
        return CatalogInteractorImpl(catalogRepository, favoriteRepository)
    }

    @Provides
    fun provideFavoriteInteractor(
        favoriteRepository: FavoriteRepository
    ): FavoriteInteractor {
        return FavoriteInteractorImpl(favoriteRepository)
    }

    @Provides
    fun provideDetailsInteractor(
        favoriteRepository: FavoriteRepository
    ): DetailsInteractor {
        return DetailsInteractorImpl(favoriteRepository)
    }

    @Provides
    fun provideProfileInteractor(
        profileRepository: ProfileRepository,
        favoriteRepository: FavoriteRepository
    ): ProfileInteractor {
        return ProfileinteractorImpl(profileRepository, favoriteRepository)
    }

    @Provides
    fun provideRegistrationInteractor(
        registrationRepository: RegistrationRepository
    ): RegistrationInteractor {
        return RegistrationInteractorImpl(registrationRepository)
    }

}