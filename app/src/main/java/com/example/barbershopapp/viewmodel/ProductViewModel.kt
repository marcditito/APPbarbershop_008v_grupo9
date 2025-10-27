package com.example.barbershopapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.barbershopapp.data.AppDatabase
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.data.ProductRepository
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for exposing product data to the UI. It
 * abstracts the underlying repository and provides an insert method to
 * add default products. Observing [allProducts] allows the UI to react
 * to database updates automatically.
 */
class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    val allProducts: LiveData<List<Product>>

    init {
        val productDao = AppDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        allProducts = repository.allProducts
    }

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }
}