package com.sample.kotlin_shop_app.ui.shoppingList

import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem

interface DialogListener {
    fun onAddButtonClick(item:ShoppingItem)
}