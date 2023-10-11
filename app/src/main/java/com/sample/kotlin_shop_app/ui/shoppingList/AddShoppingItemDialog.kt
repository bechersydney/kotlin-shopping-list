package com.sample.kotlin_shop_app.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.sample.kotlin_shop_app.data.db.entities.ShoppingItem
import com.sample.kotlin_shop_app.databinding.DialogAddItemBinding

class AddShoppingItemDialog(context: Context, var callback: DialogListener) :
    AppCompatDialog(context) {
    private lateinit var mBinding: DialogAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DialogAddItemBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(mBinding.root)
        mBinding.tvAdd.setOnClickListener {
            val name = mBinding.etName.text.toString()
            val quantity = mBinding.etAmount.text.toString()
            if (name.trim().isEmpty() || quantity.trim().isEmpty()) {
                Toast.makeText(context, "Invalid inputs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                callback.onAddButtonClick(ShoppingItem(name = name, quantity = quantity.toInt()))
                dismiss()
            }
        }
        mBinding.tvCancel.setOnClickListener {
            dismiss()
        }
    }
}