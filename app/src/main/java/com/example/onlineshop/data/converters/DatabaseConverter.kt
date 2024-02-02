package com.example.onlineshop.data.converters

import com.example.onlineshop.data.db.dto.ProductEntity
import com.example.onlineshop.data.db.dto.UserDto
import com.example.onlineshop.domain.models.Feedback
import com.example.onlineshop.domain.models.Info
import com.example.onlineshop.domain.models.Price
import com.example.onlineshop.domain.models.Product
import com.example.onlineshop.domain.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class DatabaseConverter @Inject constructor() {

    fun mapProductToData(product: Product): ProductEntity {
        with(product) {
            return ProductEntity(
                available = available,
                description = description,
                feedback = convertFeedbackToJson(feedback),
                id = id,
                info = convertInfoToJson(info),
                ingredients = ingredients,
                price = convertPriceToJson(price),
                subtitle = subtitle,
                tags = convertTagsToJson(tags),
                title = title,
                isFavorite = isFavorite,
                images = convertImagesToJson(images)
            )
        }
    }

    fun mapProductToDomain(productEntity: ProductEntity): Product {
        with(productEntity) {
            return Product(
                available = available,
                description = description,
                feedback = convertFeedbackFromJson(feedback),
                id = id,
                info = convertInfoFromJson(info),
                ingredients = ingredients,
                price = convertPriceFromJson(price),
                subtitle = subtitle,
                tags = convertTagsFromJson(tags),
                title = title,
                isFavorite = isFavorite,
                images = convertImagesFromJson(images)
            )
        }
    }

    fun mapUserToData(user: User): UserDto {
        with(user) {
            return UserDto(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber
            )
        }
    }

    fun mapUserToDomain(userDto: UserDto): User {
        with(userDto) {
            return User(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber
            )
        }
    }

    private fun convertFeedbackToJson(feedback: Feedback?): String {
        return if (feedback != null) Gson().toJson(feedback) else ""
    }

    private fun convertFeedbackFromJson(json: String): Feedback? {
        return if (json.isBlank()) null else Gson().fromJson(json, Feedback::class.java)
    }

    private fun convertPriceToJson(price: Price): String {
        return Gson().toJson(price)
    }

    private fun convertPriceFromJson(json: String): Price {
        return Gson().fromJson(json, Price::class.java)
    }

    private fun convertTagsToJson(tags: List<String>): String {
        return Gson().toJson(tags)
    }

    private fun convertTagsFromJson(json: String): List<String> {
        val typeToken = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    private fun convertInfoToJson(info: List<Info>): String {
        return Gson().toJson(info)
    }

    private fun convertInfoFromJson(json: String): List<Info> {
        val typeToken = object : TypeToken<List<Info>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    private fun convertImagesToJson(images: List<Int>): String{
        return Gson().toJson(images)
    }

    private fun convertImagesFromJson(json: String): List<Int>{
        val typeToken = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(json,typeToken)
    }

}