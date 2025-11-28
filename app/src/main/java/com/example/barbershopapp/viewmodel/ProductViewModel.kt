package com.example.barbershopapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.barbershopapp.model.Product
import com.example.barbershopapp.data.repository.ProductRepository
import android.util.Log

/**
 * ViewModel para productos conectado con XANO
 */
class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _operationResult = MutableLiveData<String?>()
    val operationResult: LiveData<String?> = _operationResult

    companion object {
        private const val TAG = "ProductViewModel"
    }

    init {
        _products.value = emptyList()
        _isLoading.value = false
        _error.value = null

        // Cargar productos de prueba inmediatamente
        loadSampleProducts()
    }

    private fun loadSampleProducts() {
        Log.d(TAG, "Cargando productos de prueba...")
        val sampleProducts = listOf(
            Product(
                id = 1,
                name = "Champú Premium",
                description = "Champú profesional para todo tipo de cabello",
                price = 25.99,
                stock = 15,
                category = "Cuidado capilar"
            ),
            Product(
                id = 2,
                name = "Gel Fijador Fuerte",
                description = "Gel de fijación extrema para peinados duraderos",
                price = 18.50,
                stock = 22,
                category = "Styling"
            ),
            Product(
                id = 3,
                name = "Aceite para Barba",
                description = "Aceite nutritivo para barba suave y sedosa",
                price = 32.00,
                stock = 8,
                category = "Barba"
            ),
            Product(
                id = 4,
                name = "Pomada Matt",
                description = "Pomada con acabado mate para looks naturales",
                price = 22.75,
                stock = 12,
                category = "Styling"
            ),
            Product(
                id = 5,
                name = "Aftershave Mentol",
                description = "Loción aftershave con mentol refrescante",
                price = 16.99,
                stock = 18,
                category = "Aftershave"
            ),
            Product(
                id = 6,
                name = "Cera para Bigote",
                description = "Cera especializada para styling de bigotes",
                price = 14.50,
                stock = 10,
                category = "Barba"
            )
        )

        _products.value = sampleProducts
        Log.d(TAG, "Productos de prueba cargados: ${sampleProducts.size} productos")
    }

    fun loadProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                Log.d(TAG, "Intentando cargar productos desde XANO...")

                val result = repository.getProducts()

                if (result.isNotEmpty()) {
                    // Si hay productos desde XANO, usar esos
                    _products.value = result
                    Log.d(TAG, "Productos cargados exitosamente desde XANO: ${result.size} productos")
                } else {
                    Log.w(TAG, "No se encontraron productos en XANO, manteniendo productos de prueba")
                }

                _isLoading.value = false

            } catch (e: Exception) {
                Log.e(TAG, "Error loading products from XANO", e)
                _error.value = "Error de conexión: ${e.message}"
                _isLoading.value = false

                // Mantener los productos de prueba si hay un error
                if (_products.value.isNullOrEmpty()) {
                    Log.d(TAG, "Usando productos de prueba debido a error de conexión")
                    loadSampleProducts()
                }
            }
        }
    }

    fun createProduct(
        name: String,
        description: String,
        price: Double,
        imageUrl: String = "",
        category: String = "",
        stock: Int = 0
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                Log.d(TAG, "Creating product: $name")

                val product = Product(
                    id = 0, // Se genera automáticamente en el backend
                    name = name,
                    description = description,
                    price = price,
                    image_url = imageUrl,
                    category = category,
                    stock = stock
                )

                val createdProduct = repository.createProduct(product)
                if (createdProduct != null) {
                    _operationResult.value = "Producto creado exitosamente"
                    loadProducts() // Recargar la lista
                } else {
                    _operationResult.value = "Error al crear el producto"
                }

                _isLoading.value = false

            } catch (e: Exception) {
                Log.e(TAG, "Error creating product", e)
                _error.value = "Error al crear producto: ${e.message}"
                _operationResult.value = "Error al crear el producto"
                _isLoading.value = false
            }
        }
    }

    fun deleteProduct(productId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                Log.d(TAG, "Deleting product with ID: $productId")

                val success = repository.deleteProduct(productId)
                if (success) {
                    _operationResult.value = "Producto eliminado exitosamente"
                    loadProducts() // Recargar la lista
                } else {
                    _operationResult.value = "Error al eliminar el producto"
                }

                _isLoading.value = false

            } catch (e: Exception) {
                Log.e(TAG, "Error deleting product", e)
                _error.value = "Error al eliminar producto: ${e.message}"
                _operationResult.value = "Error al eliminar el producto"
                _isLoading.value = false
            }
        }
    }

    fun refreshProducts() {
        Log.d(TAG, "Refreshing products...")
        loadProducts()
    }

    fun clearError() {
        _error.value = null
    }

    fun clearOperationResult() {
        _operationResult.value = null
    }
}
