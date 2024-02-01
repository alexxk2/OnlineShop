package com.example.onlineshop.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("user_id") val userId: Int = 0,
    @ColumnInfo("first_name") val firstName: String,
    @ColumnInfo("last_name") val lastName: String,
    @ColumnInfo("phone_number") val phoneNumber: String
)
