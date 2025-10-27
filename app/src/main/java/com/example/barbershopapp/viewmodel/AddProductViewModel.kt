package com.example.barbershopapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.barbershopapp.data.AppDatabase
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.data.ProductRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for the AddProductActivity. Handles inserting new
 * products into the database. Using a ViewModel allows the insertion
 * to survive configuration changes and keeps the Activity free of
 * business logic.
 */
class AddProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository

    init {
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
    }

    fun addProduct(name: String, description: String, price: Double, imageUri: String?) = viewModelScope.launch {
        repository.insert(Product(name = name, description = description, price = price, imageUri = imageUri))
    }
}