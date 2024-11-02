package com.example.animation2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animation2.models.Product
import com.example.animation2.R

class CartAdapter(private val cartList: List<Product>, private val onProductClick: (Product) -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.product_image_cart)
        val productName: TextView = view.findViewById(R.id.product_name_cart)
        val productPrice: TextView = view.findViewById(R.id.product_price_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartList[position]
        holder.productImage.setImageResource(product.imageResource)
        holder.productName.text = product.name
        holder.productPrice.text = product.price.toString()

        holder.itemView.setOnClickListener { onProductClick(product) }
    }

    override fun getItemCount() = cartList.size
}
