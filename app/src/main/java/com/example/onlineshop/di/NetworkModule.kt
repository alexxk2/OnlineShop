package com.example.onlineshop.di

import com.example.onlineshop.data.converters.NetworkConverter
import com.example.onlineshop.data.db.RoomStorage
import com.example.onlineshop.data.network.CatalogApi
import com.example.onlineshop.data.network.NetworkClient
import com.example.onlineshop.data.network.impl.NetworkClientImpl
import com.example.onlineshop.data.repositories.CatalogRepositoryImpl
import com.example.onlineshop.domain.repositories.CatalogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val baseUrl = "https://run.mocky.io"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatalogApi(retrofit: Retrofit): CatalogApi {
        return retrofit.create(CatalogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkClient(retrofitService: CatalogApi): NetworkClient {
        return NetworkClientImpl(retrofitService)
    }

    @Provides
    @Singleton
    fun provideCatalogRepository(
        converter: NetworkConverter,
        networkClient: NetworkClient,
        roomStorage: RoomStorage
    ): CatalogRepository {
        return CatalogRepositoryImpl(converter, networkClient, roomStorage)
    }

}