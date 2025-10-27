package com.example.barbershopapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.databinding.ActivityMainBinding
import com.example.barbershopapp.viewmodel.CartViewModel
import com.example.barbershopapp.viewmodel.ProductViewModel

/**
 * The entry point into the application. Displays a list of products and
 * allows the user to navigate to the cart or to product details. It
 * also inserts a few sample products on first run so that the list
 * isn't empty when the app starts.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        adapter = ProductAdapter(emptyList(), onItemClick = { product: Product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("productId", product.id)
            startActivity(intent)
        }, onAddToCart = { product: Product ->
            cartViewModel.addToCart(product)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        productViewModel.allProducts.observe(this) { products ->
            adapter.updateData(products)
            // Insert default products if the list is empty
            if (products.isEmpty()) {
                insertDefaultProducts()
            }
        }

        binding.fabCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    /**
     * Inserts a few hard‑coded products into the database. This is
     * helpful to demonstrate functionality without requiring a backend or
     * user input on first launch.
     */
    private fun insertDefaultProducts() {
        val defaultProducts = listOf(
            com.example.barbershopapp.data.Product(name = "Cera para Cabello", description = "Cera moldeadora para peinar", price = 5.99),
            com.example.barbershopapp.data.Product(name = "Aceite para Barba", description = "Aceite nutriente para barba", price = 9.49),
            com.example.barbershopapp.data.Product(name = "Shampoo para Cabello", description = "Shampoo suave para uso diario", price = 4.29)
        )
        defaultProducts.forEach { product ->
            productViewModel.insert(product)
        }
    }
}