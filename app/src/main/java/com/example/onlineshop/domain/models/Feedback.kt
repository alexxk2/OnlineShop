package com.example.onlineshop.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feedback(
    val count: Int,
    val rating: Double
): Parcelable