package com.example.onlineshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineshop.data.db.dto.ProductEntity
import com.example.onlineshop.data.db.dto.UserDto

@Database(
    entities = [ProductEntity::class, UserDto::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun profileDao(): ProfileDao
}