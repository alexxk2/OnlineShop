package com.example.onlineshop.data.network.dto

data class PriceDto(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)