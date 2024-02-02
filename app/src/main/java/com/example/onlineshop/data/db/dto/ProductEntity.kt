package com.example.onlineshop.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products")
data class ProductEntity(
    val available: Int,
    val description: String,
    val feedback: String,
    @PrimaryKey(autoGenerate = false) val id: String,
    val info: String,
    val ingredients: String,
    val price: String,
    val subtitle: String,
    val tags: String,
    val title: String,
    @ColumnInfo("is_favorite")val isFavorite: Boolean = false,
    val images: String
)
