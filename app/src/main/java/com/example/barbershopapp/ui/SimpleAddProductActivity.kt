package com.example.barbershopapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.R

/**
 * Actividad simple de fallback para agregar productos
 * Se usa cuando AddProductActivity falla
 */
class SimpleAddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_simple_add_product)

            setupToolbar()
            setupButtons()

        } catch (e: Exception) {
            // Último recurso: UI básica sin XML
            setContentView(android.R.layout.simple_list_item_1)
            val textView = findViewById<android.widget.TextView>(android.R.id.text1)
            textView?.apply {
                text = "Agregar Producto\n\nFuncionalidad en desarrollo\n\nPresiona atrás para volver"
                textSize = 16f
                gravity = android.view.Gravity.CENTER
                setPadding(32, 32, 32, 32)
            }
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            title = "Agregar Producto"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupButtons() {
        // Configurar botones básicos si existen en el layout
        findViewById<android.widget.Button>(R.id.btnSave)?.setOnClickListener {
            Toast.makeText(this, "Funcionalidad próximamente", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
