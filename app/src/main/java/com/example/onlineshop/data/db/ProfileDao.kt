package com.example.onlineshop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshop.data.db.dto.UserDto

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewItem(userDto: UserDto)

    @Query("SELECT * FROM users")
    suspend fun getAllItems(): List<UserDto>

    @Query("DELETE FROM users")
    suspend fun deleteAllItems()
}