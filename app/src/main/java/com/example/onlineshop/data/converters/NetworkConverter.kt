package com.example.onlineshop.data.converters

import com.example.onlineshop.data.network.dto.FeedbackDto
import com.example.onlineshop.data.network.dto.InfoDto
import com.example.onlineshop.data.network.dto.PriceDto
import com.example.onlineshop.data.network.dto.ProductDto
import com.example.onlineshop.domain.models.Feedback
import com.example.onlineshop.domain.models.Info
import com.example.onlineshop.domain.models.Price
import com.example.onlineshop.domain.models.Product
import javax.inject.Inject

class NetworkConverter @Inject constructor() {

    fun mapProductToData(product: Product): ProductDto {
        with(product) {
            return ProductDto(
                available = available,
                description = description,
                feedback = feedback?.let {
                    FeedbackDto(
                        count = feedback.count,
                        rating = feedback.rating
                    )
                },
                id = id,
                info = info.map {
                    InfoDto(
                        title = it.title,
                        value = it.value
                    )
                },
                ingredients = ingredients,
                price = PriceDto(
                    discount = price.discount,
                    price = price.price,
                    priceWithDiscount = price.priceWithDiscount,
                    unit = price.unit
                ),
                subtitle = subtitle,
                tags = tags,
                title = title,
                isFavorite = isFavorite,
                images = images
            )
        }
    }

    fun mapProductToDomain(productDto: ProductDto): Product {
        with(productDto) {
            return Product(
                available = available,
                description = description,
                feedback = feedback?.let {
                    Feedback(
                        count = feedback.count,
                        rating = feedback.rating
                    )
                },
                id = id,
                info = info.map {
                    Info(
                        title = it.title,
                        value = it.value
                    )
                },
                ingredients = ingredients,
                price = Price(
                    discount = price.discount,
                    price = price.price,
                    priceWithDiscount = price.priceWithDiscount,
                    unit = price.unit
                ),
                subtitle = subtitle,
                tags = tags,
                title = title,
                images = images
            )
        }
    }

    fun mapProductToDomainFavorite(productDto: ProductDto): Product {
        with(productDto) {
            return Product(
                available = available,
                description = description,
                feedback = feedback?.let {
                    Feedback(
                        count = feedback.count,
                        rating = feedback.rating
                    )
                },
                id = id,
                info = info.map {
                    Info(
                        title = it.title,
                        value = it.value
                    )
                },
                ingredients = ingredients,
                price = Price(
                    discount = price.discount,
                    price = price.price,
                    priceWithDiscount = price.priceWithDiscount,
                    unit = price.unit
                ),
                subtitle = subtitle,
                tags = tags,
                title = title,
                isFavorite = true,
                images = images
            )
        }
    }
}