package com.example.barbershopapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * ProductDetailActivity - Actividad para mostrar detalles de un producto
 */
class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ProductDetailActivity", "Actividad iniciada")

        val productId = intent.getLongExtra("PRODUCT_ID", -1)

        // UI básica temporal
        setContentView(android.R.layout.simple_list_item_1)

        val textView = findViewById<android.widget.TextView>(android.R.id.text1)
        textView?.apply {
            text = "📱 Detalles del Producto\n\n🚧 Función en desarrollo\n\nID: $productId\n\nAquí se mostrarán los detalles completos del producto seleccionado"
            textSize = 16f
            setPadding(32, 32, 32, 32)
            gravity = android.view.Gravity.CENTER

            setOnClickListener {
                Toast.makeText(this@ProductDetailActivity, "Función próximamente disponible", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
