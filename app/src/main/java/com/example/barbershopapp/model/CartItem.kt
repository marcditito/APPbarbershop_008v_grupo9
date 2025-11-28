package com.example.barbershopapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Modelo para productos en el carrito
 * VERSIÓN FULLSTACK PROFESIONAL
 */
@Parcelize
data class CartItem(
    val productId: String,
    val productName: String,
    val productPrice: Double,
    val quantity: Int = 1,
    val productImage: String? = null
) : Parcelable {

    val totalPrice: Double
        get() = productPrice * quantity
}
