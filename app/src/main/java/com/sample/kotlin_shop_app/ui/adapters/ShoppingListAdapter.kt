package com.sample.kotlin_shop_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem
import com.sample.kotlin_shop_app.databinding.ShoppingItemBinding
import com.sample.kotlin_shop_app.ui.shoppingList.ShoppingViewModel

class ShoppingListAdapter(
    private val items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder>() {

    private lateinit var _binding: ShoppingItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        _binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(_binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                _binding.tvName.text = this.name
                _binding.tvAmount.text = this.quantity.toString()
            }
        }


    }

    inner class ShoppingViewHolder(binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}