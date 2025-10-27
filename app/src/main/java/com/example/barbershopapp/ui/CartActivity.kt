package com.example.barbershopapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershopapp.R
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.databinding.ActivityCartBinding
import com.example.barbershopapp.viewmodel.CartViewModel
import java.text.NumberFormat
import java.util.*

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        observeCart()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            products = emptyList(),
            onRemoveItem = { product ->
                viewModel.removeFromCart(product)
            }
        )

        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            this.adapter = this@CartActivity.adapter
        }

        binding.buttonCheckout.setOnClickListener {
            // Implementar checkout aquí
            viewModel.clearCart()
        }
    }

    private fun observeCart() {
        viewModel.cartItems.observe(this) { products ->
            adapter.updateCart(products)
            updateTotal(products)
        }
    }

    private fun updateTotal(products: List<Product>) {
        val total = products.fold(0.0) { acc, product -> acc + product.price }
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        binding.textTotal.text = numberFormat.format(total)
    }
}
