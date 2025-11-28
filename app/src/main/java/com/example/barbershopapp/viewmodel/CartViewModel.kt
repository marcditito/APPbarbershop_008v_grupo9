package com.example.barbershopapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.barbershopapp.model.ProductItem
import com.example.barbershopapp.model.CartItem
import android.util.Log

/**
 * ViewModel simplificado para el carrito de compras (local)
 * VERSIÓN FULLSTACK PROFESIONAL
 */
class CartViewModel : ViewModel() {

    // Lista de productos en el carrito (local)
    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> = _total

    private val _operationResult = MutableLiveData<String?>()
    val operationResult: LiveData<String?> = _operationResult

    init {
        _cartItems.value = mutableListOf()
        _total.value = 0.0
    }

    fun addToCart(product: ProductItem, quantity: Int = 1) {
        val currentItems = _cartItems.value ?: mutableListOf()

        // Buscar si el producto ya está en el carrito
        val existingItemIndex = currentItems.indexOfFirst { it.productId == product.id }

        if (existingItemIndex != -1) {
            // Producto ya existe, aumentar cantidad
            val existingItem = currentItems[existingItemIndex]
            currentItems[existingItemIndex] = existingItem.copy(quantity = existingItem.quantity + quantity)
        } else {
            // Nuevo producto en el carrito
            val cartItem = CartItem(
                productId = product.id,
                productName = product.name,
                productPrice = product.price,
                quantity = quantity
            )
            currentItems.add(cartItem)
        }

        _cartItems.value = currentItems
        calculateTotal()

        Log.d("CartViewModel", "Producto agregado: ${product.name}, Cantidad: $quantity")
        _operationResult.value = "Producto agregado al carrito"
    }

    fun removeFromCart(productId: String) {
        val currentItems = _cartItems.value ?: mutableListOf()
        currentItems.removeAll { it.productId == productId }
        _cartItems.value = currentItems
        calculateTotal()

        Log.d("CartViewModel", "Producto removido del carrito")
        _operationResult.value = "Producto eliminado del carrito"
    }

    fun updateQuantity(productId: String, newQuantity: Int) {
        val currentItems = _cartItems.value ?: mutableListOf()
        val itemIndex = currentItems.indexOfFirst { it.productId == productId }

        if (itemIndex != -1) {
            if (newQuantity > 0) {
                currentItems[itemIndex] = currentItems[itemIndex].copy(quantity = newQuantity)
            } else {
                currentItems.removeAt(itemIndex)
            }
            _cartItems.value = currentItems
            calculateTotal()

            Log.d("CartViewModel", "Cantidad actualizada para producto: $productId, Nueva cantidad: $newQuantity")
            _operationResult.value = "Cantidad actualizada"
        }
    }

    fun clearCart() {
        _cartItems.value = mutableListOf()
        _total.value = 0.0
        _operationResult.value = "Carrito vaciado"
        Log.d("CartViewModel", "Carrito vaciado")
    }

    fun getCartItemCount(): Int {
        return _cartItems.value?.sumOf { it.quantity } ?: 0
    }

    private fun calculateTotal() {
        val items = _cartItems.value ?: mutableListOf()
        val totalAmount = items.sumOf { it.totalPrice }
        _total.value = totalAmount
        Log.d("CartViewModel", "Total calculado: $totalAmount")
    }

    fun clearOperationResult() {
        _operationResult.value = null
    }
}
