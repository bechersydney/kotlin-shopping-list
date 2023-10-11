package com.sample.kotlin_shop_app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {
    // if id exist? update : insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)
    @Query("SELECT * From shopping_items")
    fun getItems(): LiveData<List<ShoppingItem>>
}