package com.example.barbershopapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.barbershopapp.data.CartItem
import com.example.barbershopapp.data.CartRepository
import com.example.barbershopapp.data.Product

/**
 * ViewModel for cart operations. Delegates actions to the CartRepository
 * and exposes LiveData so the UI can observe cart changes. Contains
 * logic for calculating the total price.
 */
class CartViewModel : ViewModel() {
    private val repository = CartRepository()
    val cartItems: LiveData<MutableList<CartItem>> = repository.cartItems

    fun addToCart(product: Product) {
        repository.addItem(product)
    }

    fun removeFromCart(product: Product) {
        repository.removeItem(product)
    }

    fun getTotal(): Double {
        return repository.getTotal()
    }
}