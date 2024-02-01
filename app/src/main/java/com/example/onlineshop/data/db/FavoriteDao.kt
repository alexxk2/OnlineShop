package com.example.onlineshop.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshop.data.db.dto.ProductEntity


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewItem(productEntity: ProductEntity)

    @Delete
    suspend fun removeItem(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun getAllItems(): List<ProductEntity>

    @Query("DELETE FROM products")
    suspend fun deleteAllItems()

}