package com.example.barbershopapp.data

/**
 * Represents an item in the shopping cart. It references a Product and
 * tracks how many units of that product have been added. This class
 * deliberately does not extend a database entity because the cart is
 * managed in memory for simplicity; in a real application you could
 * persist the cart using Room as well.
 */
data class CartItem(
    val product: Product,
    var quantity: Int = 1
)