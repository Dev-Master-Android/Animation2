package com.example.animation2.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animation2.utils.AlertDialog.showRemoveFromCartDialog
import com.example.animation2.models.Product
import com.example.animation2.R
import com.example.animation2.adapter.CartAdapter
import com.example.animation2.utils.Animation.backgroundAnimation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CartActivity : AppCompatActivity() {
    private lateinit var cartAdapter: CartAdapter
    private val cartList: MutableList<Product> = mutableListOf()
    private lateinit var toolbar: Toolbar
    private lateinit var relativeLayout: RelativeLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        cartList.addAll(intent.getParcelableArrayListExtra<Product>("cart") ?: listOf())

        cartAdapter = CartAdapter(cartList) { product ->
            showRemoveFromCartDialog(product, this) { selectedProduct ->
                cartList.remove(selectedProduct)
                cartAdapter.notifyDataSetChanged()
            }
        }

        relativeLayout = findViewById(R.id.activity_cart)
        backgroundAnimation(relativeLayout)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_cart)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter

        val fab: FloatingActionButton = findViewById(R.id.fab_checkout)
        fab.setOnClickListener {
            fab.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.withEndAction {
                val intent = Intent(this, CheckoutActivity::class.java)
                intent.putParcelableArrayListExtra("cart", ArrayList(cartList))
                startActivity(intent)
            }.start()
        }
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
