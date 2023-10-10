package com.sample.kotlin_shop_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sample.kotlin_shop_app.data.db.ShoppingDatabase
import com.sample.kotlin_shop_app.data.repository.ShoppingRepository
import com.sample.kotlin_shop_app.ui.shoppingList.ShoppingViewModel
import com.sample.kotlin_shop_app.ui.shoppingList.ShoppingViewModelFactory

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]
    }
}