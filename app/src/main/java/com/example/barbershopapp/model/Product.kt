package com.example.barbershopapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image_url: String? = null,
    val category: String? = null,
    val stock: Int = 0,
    val created_at: String? = null,
    val updated_at: String? = null
) : Parcelable
