package com.example.barbershopapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * In‑memory repository for shopping cart items. This demonstrates state
 * management separate from the UI. In a production app you could
 * persist the cart using Room or another persistence mechanism.
 */
class CartRepository {
    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    /** Adds a product to the cart or increases quantity if already present. */
    fun addItem(product: Product) {
        val items = _cartItems.value ?: mutableListOf()
        val existing = items.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            items.add(CartItem(product))
        }
        _cartItems.value = items
    }

    /** Removes a product from the cart or decreases quantity. */
    fun removeItem(product: Product) {
        val items = _cartItems.value ?: mutableListOf()
        val existing = items.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity--
            if (existing.quantity <= 0) items.remove(existing)
        }
        _cartItems.value = items
    }

    /** Calculates the total cost of items in the cart. */
    fun getTotal(): Double {
        return _cartItems.value?.sumOf { it.product.price * it.quantity } ?: 0.0
    }
}