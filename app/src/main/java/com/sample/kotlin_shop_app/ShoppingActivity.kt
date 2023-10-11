package com.sample.kotlin_shop_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.sample.kotlin_shop_app.data.db.ShoppingDatabase
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem
import com.sample.kotlin_shop_app.data.repository.ShoppingRepository
import com.sample.kotlin_shop_app.databinding.ActivityShopBinding
import com.sample.kotlin_shop_app.ui.adapters.ShoppingListAdapter
import com.sample.kotlin_shop_app.ui.shoppingList.AddShoppingItemDialog
import com.sample.kotlin_shop_app.ui.shoppingList.DialogListener
import com.sample.kotlin_shop_app.ui.shoppingList.ShoppingViewModel
import com.sample.kotlin_shop_app.ui.shoppingList.ShoppingViewModelFactory

class ShopActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityShopBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]
        // set empty list in initialization
        val adapter = ShoppingListAdapter(listOf(), viewModel )
        mBinding.itemList.layoutManager = LinearLayoutManager(this)
        mBinding.itemList.adapter = adapter

        viewModel.getItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        mBinding.addItem.setOnClickListener{
            AddShoppingItemDialog(this, object: DialogListener{
                override fun onAddButtonClick(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}