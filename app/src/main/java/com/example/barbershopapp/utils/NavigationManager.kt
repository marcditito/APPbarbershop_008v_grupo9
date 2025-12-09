package com.example.barbershopapp.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.barbershopapp.ui.AddProductActivity
import com.example.barbershopapp.ui.CartActivity
import com.example.barbershopapp.ui.MainActivity
import com.example.barbershopapp.ui.ProductDetailActivity

/**
 * Gestor de navegación centralizado
 * Facilita la navegación entre pantallas con animaciones
 */
object NavigationManager {

    private const val TAG = "NavigationManager"

    /**
     * Navegar a MainActivity
     */
    fun navigateToHome(context: Context) {
        try {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            context.startActivity(intent)
            Log.d(TAG, "✓ Navegando a Home")
            // Vibración suave
            NativeAndroidUtils.vibrateClick(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error navegando a Home", e)
        }
    }

    /**
     * Navegar a pantalla de agregar producto
     */
    fun navigateToAddProduct(context: Context) {
        try {
            val intent = Intent(context, AddProductActivity::class.java)
            context.startActivity(intent)
            Log.d(TAG, "✓ Navegando a Agregar Producto")
            // Vibración éxito
            NativeAndroidUtils.vibrateSuccess(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error navegando a Agregar Producto", e)
            // Vibración error
            NativeAndroidUtils.vibrateError(context)
        }
    }

    /**
     * Navegar a carrito
     */
    fun navigateToCart(context: Context) {
        try {
            val intent = Intent(context, CartActivity::class.java)
            context.startActivity(intent)
            Log.d(TAG, "✓ Navegando a Carrito")
            NativeAndroidUtils.vibrateClick(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error navegando a Carrito", e)
            NativeAndroidUtils.vibrateError(context)
        }
    }

    /**
     * Navegar a detalle del producto
     */
    fun navigateToProductDetail(context: Context, productId: String) {
        try {
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("product_id", productId)
            }
            context.startActivity(intent)
            Log.d(TAG, "✓ Navegando a Detalle del Producto: $productId")
            NativeAndroidUtils.vibrateClick(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error navegando a Detalle del Producto", e)
        }
    }
}

