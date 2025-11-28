package com.example.barbershopapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Modelo para productos de la barbería
 * VERSIÓN FULLSTACK PROFESIONAL
 */
@Parcelize
data class ProductItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String = "Barbería",
    val imageUrl: String? = null,
    val inStock: Boolean = true
) : Parcelable
