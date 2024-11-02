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
import com.example.animation2.utils.AlertDialog.showProductDialog
import com.example.animation2.models.Product
import com.example.animation2.R
import com.example.animation2.adapter.ProductAdapter
import com.example.animation2.utils.Animation.backgroundAnimation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductListActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var productAdapter: ProductAdapter
    private lateinit var relativeLayout: RelativeLayout
    private val productList = listOf(
        Product(R.drawable.product1, "Товар 1", 9.99),
        Product(R.drawable.product2, "Товар 2", 19.99),
        Product(R.drawable.product3, "Товар 3", 29.99),
        Product(R.drawable.product4, "Товар 4", 39.99),
        Product(R.drawable.product5, "Товар 5", 49.99),
        Product(R.drawable.product6, "Товар 6", 59.99),
        Product(R.drawable.product7, "Товар 7", 69.99),
    )

    private val cartList = mutableListOf<Product>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        toolbar = findViewById(R.id.toolbarProductList)
        setSupportActionBar(toolbar)
        productAdapter = ProductAdapter(productList) { product ->
            showProductDialog(product, this) { selectedProduct ->
                cartList.add(selectedProduct)
            }
        }
        relativeLayout = findViewById(R.id.activity_product_list)
        backgroundAnimation(relativeLayout)


        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            fab.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.withEndAction {
                val intent = Intent(this, CartActivity::class.java)
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
