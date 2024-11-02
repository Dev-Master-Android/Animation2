package com.example.animation2.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.animation2.models.Product
import com.example.animation2.R
import com.example.animation2.utils.Animation.backgroundAnimation


class CheckoutActivity : AppCompatActivity() {
    private val cartList: MutableList<Product> = mutableListOf()
    private lateinit var toolbar: Toolbar
    private lateinit var relativeLayout: RelativeLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        toolbar = findViewById(R.id.toolbarCheckout)
        setSupportActionBar(toolbar)
        cartList.addAll(intent.getParcelableArrayListExtra<Product>("cart") ?: listOf())

        relativeLayout = findViewById(R.id.activity_checkout)
        backgroundAnimation(relativeLayout)

        val receiptTextView: TextView = findViewById(R.id.receipt_text_view)
        val receipt = generateReceipt(cartList)
        receiptTextView.text = receipt
    }

    private fun generateReceipt(cartList: List<Product>): String {
        val receiptBuilder = StringBuilder()
        var total = 0.0

        receiptBuilder.append("Чек покупок\n\n")
        cartList.forEach { product ->
            receiptBuilder.append("${product.name}: ${product.price} руб.\n")
            total += product.price.toInt()
        }
        receiptBuilder.append("\nИтого: $total руб.")

        return receiptBuilder.toString()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_exit -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
