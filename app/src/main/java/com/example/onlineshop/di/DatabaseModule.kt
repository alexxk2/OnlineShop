package com.example.onlineshop.di

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.data.converters.DatabaseConverter
import com.example.onlineshop.data.db.AppDatabase
import com.example.onlineshop.data.db.FavoriteDao
import com.example.onlineshop.data.db.ProfileDao
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.data.db.impl.RoomStorageImpl
import com.example.onlineshop.data.repositories.FavoriteRepositoryImpl
import com.example.onlineshop.data.repositories.ProfileRepositoryImpl
import com.example.onlineshop.data.repositories.RegistrationRepositoryImpl
import com.example.onlineshop.domain.repositories.FavoriteRepository
import com.example.onlineshop.domain.repositories.ProfileRepository
import com.example.onlineshop.domain.repositories.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideProfileDao(database: AppDatabase): ProfileDao {
        return database.profileDao()
    }

    @Provides
    @Singleton
    fun provideRoomStorage(favoriteDao: FavoriteDao, profileDao: ProfileDao): RoomStorage {
        return RoomStorageImpl(favoriteDao, profileDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        roomStorage: RoomStorage,
        databaseConverter: DatabaseConverter
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(roomStorage, databaseConverter)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        roomStorage: RoomStorage,
        databaseConverter: DatabaseConverter
    ): ProfileRepository {
        return ProfileRepositoryImpl(roomStorage, databaseConverter)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(
        roomStorage: RoomStorage,
        databaseConverter: DatabaseConverter
    ): RegistrationRepository {
        return RegistrationRepositoryImpl(roomStorage, databaseConverter)
    }

    companion object {
        const val DATABASE = "app_database"
    }
}