package com.example.animation2.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.animation2.models.Product

object AlertDialog {
    fun showProductDialog(product: Product, context: Context, onAddToCart: (Product) -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(product.name)
        builder.setMessage("Цена: ${product.price}")
        builder.setPositiveButton("В корзину") { dialog, _ ->
            onAddToCart(product)
            dialog.dismiss()
        }
        builder.setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
    fun showRemoveFromCartDialog(product: Product, context: Context, onRemoveFromCart: (Product) -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(product.name)
        builder.setMessage("Удалить из корзины?")
        builder.setPositiveButton("Удалить") { dialog, _ ->
            onRemoveFromCart(product)
            dialog.dismiss()
        }
        builder.setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
}