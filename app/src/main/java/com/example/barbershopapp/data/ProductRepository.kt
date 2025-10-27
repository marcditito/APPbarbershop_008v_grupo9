package com.example.barbershopapp.data

import androidx.lifecycle.LiveData

/**
 * Repository for managing product data. Hides the underlying data source
 * from the rest of the app and provides a clean API. Here we simply
 * delegate to a Room DAO but in a more complex application you might
 * combine local and remote data sources.
 */
class ProductRepository(private val productDao: ProductDao) {
    val allProducts: LiveData<List<Product>> = productDao.getAll()

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }

    suspend fun delete(product: Product) {
        productDao.delete(product)
    }
}