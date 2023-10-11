package com.sample.kotlin_shop_app.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem
import com.sample.kotlin_shop_app.databinding.ShoppingItemBinding
import com.sample.kotlin_shop_app.ui.shoppingList.ShoppingViewModel

class ShoppingListAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {

    private lateinit var _binding: ShoppingItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        _binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(_binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]
        _binding.tvAmount.text = currentItem.quantity.toString()
        _binding.tvName.text = currentItem.name
        _binding.ivDelete.setOnClickListener{
            viewModel.deleteItem(currentItem)
        }
        _binding.ivPlus.setOnClickListener {
            currentItem.quantity++
            viewModel.upsert(currentItem)
        }
        _binding.ivMinus.setOnClickListener {
            if(currentItem.quantity != 0) {
                currentItem.quantity--
                viewModel.upsert(currentItem)
            } else {
                Log.d("TAG", "onBindViewHolder: quantity is 0")
            }
        }


    }

    inner class ShoppingViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}