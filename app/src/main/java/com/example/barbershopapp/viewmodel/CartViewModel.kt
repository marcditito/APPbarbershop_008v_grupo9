package com.example.barbershopapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barbershopapp.data.Product

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<Product>>(emptyList())
    val cartItems: LiveData<List<Product>> = _cartItems

    fun addToCart(product: Product) {
        val currentItems = _cartItems.value.orEmpty().toMutableList()
        currentItems.add(product)
        _cartItems.value = currentItems
    }

    fun removeFromCart(product: Product) {
        val currentItems = _cartItems.value.orEmpty().toMutableList()
        currentItems.remove(product)
        _cartItems.value = currentItems
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
