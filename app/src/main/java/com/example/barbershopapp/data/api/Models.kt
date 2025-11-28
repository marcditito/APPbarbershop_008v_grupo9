package com.example.barbershopapp.data.api

import com.squareup.moshi.JsonClass


// Models para productos
@JsonClass(generateAdapter = true)
data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String? = null,
    val category: String? = null,
    val stock: Int? = null
)

@JsonClass(generateAdapter = true)
data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String? = null,
    val category: String? = null,
    val stock: Int? = null
)

@JsonClass(generateAdapter = true)
data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String? = null,
    val category: String? = null,
    val stock: Int? = null
)
