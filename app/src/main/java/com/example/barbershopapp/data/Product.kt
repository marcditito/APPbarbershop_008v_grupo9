package com.example.barbershopapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a product sold in the barber shop. Each product has a
 * unique ID, a name, description, price and an optional image URI
 * pointing to where its photo is stored on the device. This entity is
 * persisted using Room to provide local storage for the application
 * and demonstrates how to decouple data storage from the UI layer.
 */
@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
    val imageUri: String? = null
)