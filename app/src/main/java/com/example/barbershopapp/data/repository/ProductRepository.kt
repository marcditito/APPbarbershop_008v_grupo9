package com.example.barbershopapp.data.repository

import com.example.barbershopapp.data.api.NetworkModule
import com.example.barbershopapp.data.api.XanoProductApi
import com.example.barbershopapp.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

class ProductRepository {

    private val api: XanoProductApi = NetworkModule.provideXanoProductApi()

    suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getProducts()
                if (response.isSuccessful) {
                    response.body() ?: emptyList()
                } else {
                    Log.e("ProductRepository", "Error getting products: ${response.code()}")
                    emptyList()
                }
            } catch (e: Exception) {
                Log.e("ProductRepository", "Exception getting products", e)
                emptyList()
            }
        }
    }

    suspend fun getProduct(productId: Int): Product? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getProduct(productId)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("ProductRepository", "Error getting product: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("ProductRepository", "Exception getting product", e)
                null
            }
        }
    }

    suspend fun createProduct(product: Product): Product? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.createProduct(product)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("ProductRepository", "Error creating product: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("ProductRepository", "Exception creating product", e)
                null
            }
        }
    }

    suspend fun updateProduct(productId: Int, product: Product): Product? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.updateProduct(productId, product)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("ProductRepository", "Error updating product: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("ProductRepository", "Exception updating product", e)
                null
            }
        }
    }

    suspend fun deleteProduct(productId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.deleteProduct(productId)
                response.isSuccessful
            } catch (e: Exception) {
                Log.e("ProductRepository", "Exception deleting product", e)
                false
            }
        }
    }
}
