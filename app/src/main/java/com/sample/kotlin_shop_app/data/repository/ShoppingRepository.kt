package com.sample.kotlin_shop_app.data.repository

import com.sample.kotlin_shop_app.data.db.ShoppingDatabase
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().deleteItem(item)
    fun getItems() = db.getShoppingDao().getItems()
}