package com.example.barbershopapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershopapp.databinding.ActivityCartBinding
import com.example.barbershopapp.viewmodel.CartViewModel

/**
 * Shows the list of items that the user has added to their cart and the
 * total price. The cart is observed via LiveData so updates are
 * reflected automatically.
 */
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CartAdapter(emptyList())
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = adapter

        cartViewModel.cartItems.observe(this) { items ->
            adapter.updateData(items)
            binding.textTotal.text = "Total: $${'$'}{String.format("%.2f", cartViewModel.getTotal())}"
        }
    }
}