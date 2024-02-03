package com.example.onlineshop.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val available: Int,
    val description: String,
    val feedback: Feedback?,
    val id: String,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val tags: List<String>,
    val title: String,
    val isFavorite: Boolean = false,
    val images: List<Int> = emptyList()
): Parcelable