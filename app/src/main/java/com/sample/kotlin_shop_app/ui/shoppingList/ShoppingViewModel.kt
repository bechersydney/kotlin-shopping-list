package com.sample.kotlin_shop_app.ui.shoppingList

import androidx.lifecycle.ViewModel
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem
import com.sample.kotlin_shop_app.data.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repo: ShoppingRepository
) : ViewModel() {
    // Dispatcher.Main since we are using local db (Room)
    // change to Dispatcher.IO when using webservice
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repo.upsert(item)
    }

    fun getItems() = CoroutineScope(Dispatchers.Main).launch {
        repo.getItems()
    }

    fun deleteItem(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repo.delete(item)
    }
}