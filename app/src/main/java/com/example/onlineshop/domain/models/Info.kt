package com.example.onlineshop.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val title: String,
    val value: String
): Parcelable