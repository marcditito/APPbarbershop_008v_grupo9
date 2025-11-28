package com.example.barbershopapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.ScrollView
import android.graphics.Color
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.model.CartItem

/**
 * CartActivity FULLSTACK PROFESIONAL
 * Carrito de compras completamente funcional
 */
class CartActivity : AppCompatActivity() {

    private var cartItems = mutableListOf<CartItem>()
    private lateinit var cartLayout: LinearLayout
    private lateinit var totalAmountText: TextView
    private lateinit var emptyCartView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("CartActivity", "=== CartActivity FULLSTACK PROFESIONAL iniciada ===")

        try {
            // Ocultar ActionBar
            supportActionBar?.hide()

            // Recibir datos del carrito
            loadCartData()

            // Crear UI completamente funcional
            createCartUI()

            Log.d("CartActivity", "✅ CartActivity funcional inicializada exitosamente")

        } catch (e: Exception) {
            Log.e("CartActivity", "❌ Error en CartActivity", e)
            createErrorUI(e)
        }
    }

    private fun loadCartData() {
        try {
            // Recibir items del carrito desde MainActivity
            intent.getParcelableArrayListExtra<CartItem>("cart_items")?.let { items ->
                cartItems.clear()
                cartItems.addAll(items)
                Log.d("CartActivity", "Carrito cargado con ${cartItems.size} items")
            }
        } catch (e: Exception) {
            Log.e("CartActivity", "Error cargando datos del carrito", e)
        }
    }

    private fun createCartUI() {
        try {
            // ScrollView principal para contenido largo
            val scrollView = ScrollView(this).apply {
                setBackgroundColor(Color.parseColor("#121212"))
            }

            // Layout principal
            val mainLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(0, 0, 0, 0)
            }

            // Header/Toolbar
            val toolbar = createCartToolbar()
            mainLayout.addView(toolbar)

            // Contenido del carrito
            if (cartItems.isEmpty()) {
                val emptyView = createEmptyCartView()
                mainLayout.addView(emptyView)
            } else {
                val cartContent = createCartContent()
                mainLayout.addView(cartContent)
            }

            scrollView.addView(mainLayout)
            setContentView(scrollView)

            Log.d("CartActivity", "✅ UI del carrito creada exitosamente")

        } catch (e: Exception) {
            Log.e("CartActivity", "Error creando UI del carrito", e)
            createErrorUI(e)
        }
    }

    private fun createCartToolbar(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Color.parseColor("#1F1F1F"))
            setPadding(32, 32, 32, 32)
            gravity = Gravity.CENTER_VERTICAL

            // Botón volver
            val backButton = Button(this@CartActivity).apply {
                text = "← VOLVER"
                textSize = 14f
                setBackgroundColor(Color.parseColor("#333333"))
                setTextColor(Color.WHITE)
                setPadding(16, 12, 16, 12)

                setOnClickListener {
                    finishWithResult()
                }
            }
            addView(backButton)

            // Título
            val titleText = TextView(this@CartActivity).apply {
                text = "🛒 CARRITO DE COMPRAS"
                textSize = 20f
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                setPadding(32, 0, 32, 0)
            }
            addView(titleText)

            // Info items
            val itemsCountText = TextView(this@CartActivity).apply {
                text = "${cartItems.size}\nITEMS"
                textSize = 12f
                setTextColor(Color.parseColor("#B0B0B0"))
                gravity = Gravity.CENTER
            }
            addView(itemsCountText)
        }
    }

    private fun createEmptyCartView(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(64, 100, 64, 100)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

            // Icono carrito vacío
            val emptyIcon = TextView(this@CartActivity).apply {
                text = "🛒"
                textSize = 80f
                gravity = Gravity.CENTER
                setPadding(0, 0, 0, 32)
            }
            addView(emptyIcon)

            // Mensaje carrito vacío
            val emptyMessage = TextView(this@CartActivity).apply {
                text = "Tu carrito está vacío\n\n¡Agrega algunos productos desde la tienda!"
                textSize = 18f
                setTextColor(Color.parseColor("#E0E0E0"))
                gravity = Gravity.CENTER
                setPadding(0, 0, 0, 40)
            }
            addView(emptyMessage)

            // Botón ir a comprar
            val shopButton = Button(this@CartActivity).apply {
                text = "🛍️ IR A COMPRAR"
                textSize = 16f
                setBackgroundColor(Color.parseColor("#4CAF50"))
                setTextColor(Color.WHITE)
                setPadding(40, 20, 40, 20)

                setOnClickListener {
                    finishWithResult()
                }
            }
            addView(shopButton)
        }
    }

    private fun createCartContent(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)

            // Lista de productos en el carrito
            cartLayout = LinearLayout(this@CartActivity).apply {
                orientation = LinearLayout.VERTICAL
            }
            addView(cartLayout)

            // Cargar productos en el carrito
            updateCartDisplay()

            // Resumen total
            val summarySection = createSummarySection()
            addView(summarySection)

            // Botones de acción
            val actionsSection = createActionsSection()
            addView(actionsSection)
        }
    }

    private fun updateCartDisplay() {
        try {
            cartLayout.removeAllViews()

            cartItems.forEach { cartItem ->
                val productCard = createCartItemCard(cartItem)
                cartLayout.addView(productCard)
            }

            Log.d("CartActivity", "Carrito actualizado con ${cartItems.size} productos")

        } catch (e: Exception) {
            Log.e("CartActivity", "Error actualizando carrito", e)
        }
    }

    private fun createCartItemCard(cartItem: CartItem): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Color.parseColor("#2C2C2C"))
            setPadding(24, 20, 24, 20)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 16
            }
            gravity = Gravity.CENTER_VERTICAL

            // Información del producto
            val productInfo = LinearLayout(this@CartActivity).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val productName = TextView(this@CartActivity).apply {
                text = cartItem.productName
                textSize = 16f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 8)
            }
            productInfo.addView(productName)

            val productPrice = TextView(this@CartActivity).apply {
                text = "💰 $${cartItem.productPrice} c/u"
                textSize = 14f
                setTextColor(Color.parseColor("#B0B0B0"))
            }
            productInfo.addView(productPrice)

            addView(productInfo)

            // Controles de cantidad
            val quantityControls = createQuantityControls(cartItem)
            addView(quantityControls)

            // Precio total
            val totalPrice = TextView(this@CartActivity).apply {
                text = "$${cartItem.totalPrice}"
                textSize = 18f
                setTextColor(Color.parseColor("#4CAF50"))
                gravity = Gravity.CENTER
                setPadding(16, 0, 0, 0)
                minWidth = 100
            }
            addView(totalPrice)
        }
    }

    private fun createQuantityControls(cartItem: CartItem): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL

            // Botón decrementar
            val decreaseButton = Button(this@CartActivity).apply {
                text = "−"
                textSize = 18f
                setBackgroundColor(Color.parseColor("#F44336"))
                setTextColor(Color.WHITE)
                minWidth = 50
                setPadding(8, 8, 8, 8)

                setOnClickListener {
                    updateItemQuantity(cartItem, cartItem.quantity - 1)
                }
            }
            addView(decreaseButton)

            // Cantidad actual
            val quantityText = TextView(this@CartActivity).apply {
                text = cartItem.quantity.toString()
                textSize = 16f
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                minWidth = 60
                setPadding(16, 0, 16, 0)
            }
            addView(quantityText)

            // Botón incrementar
            val increaseButton = Button(this@CartActivity).apply {
                text = "+"
                textSize = 18f
                setBackgroundColor(Color.parseColor("#4CAF50"))
                setTextColor(Color.WHITE)
                minWidth = 50
                setPadding(8, 8, 8, 8)

                setOnClickListener {
                    updateItemQuantity(cartItem, cartItem.quantity + 1)
                }
            }
            addView(increaseButton)
        }
    }

    private fun createSummarySection(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#1E1E1E"))
            setPadding(32, 24, 32, 24)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 32
            }

            // Título resumen
            val summaryTitle = TextView(this@CartActivity).apply {
                text = "📋 RESUMEN DE COMPRA"
                textSize = 18f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 16)
            }
            addView(summaryTitle)

            // Subtotal
            val subtotalRow = createSummaryRow("Subtotal:", calculateSubtotal())
            addView(subtotalRow)

            // Impuestos
            val taxRow = createSummaryRow("Impuestos (10%):", calculateTax())
            addView(taxRow)

            // Línea divisoria
            val divider = View(this@CartActivity).apply {
                setBackgroundColor(Color.parseColor("#444444"))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2
                ).apply {
                    topMargin = 16
                    bottomMargin = 16
                }
            }
            addView(divider)

            // Total
            totalAmountText = TextView(this@CartActivity).apply {
                text = "💰 TOTAL: $${calculateTotal()}"
                textSize = 20f
                setTextColor(Color.parseColor("#4CAF50"))
                gravity = Gravity.CENTER
                setPadding(0, 8, 0, 8)
            }
            addView(totalAmountText)
        }
    }

    private fun createSummaryRow(label: String, amount: Double): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(0, 8, 0, 8)

            val labelText = TextView(this@CartActivity).apply {
                text = label
                textSize = 16f
                setTextColor(Color.parseColor("#B0B0B0"))
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }
            addView(labelText)

            val amountText = TextView(this@CartActivity).apply {
                text = "$$amount"
                textSize = 16f
                setTextColor(Color.WHITE)
                gravity = Gravity.END
            }
            addView(amountText)
        }
    }

    private fun createActionsSection(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 32, 0, 32)

            // Botón comprar
            val purchaseButton = Button(this@CartActivity).apply {
                text = "💳 PROCEDER A COMPRAR"
                textSize = 18f
                setBackgroundColor(Color.parseColor("#4CAF50"))
                setTextColor(Color.WHITE)
                setPadding(32, 24, 32, 24)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 16
                }

                setOnClickListener {
                    processPurchase()
                }
            }
            addView(purchaseButton)

            // Botón seguir comprando
            val continueShoppingButton = Button(this@CartActivity).apply {
                text = "🛍️ SEGUIR COMPRANDO"
                textSize = 16f
                setBackgroundColor(Color.parseColor("#2196F3"))
                setTextColor(Color.WHITE)
                setPadding(32, 20, 32, 20)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 16
                }

                setOnClickListener {
                    finishWithResult()
                }
            }
            addView(continueShoppingButton)

            // Botón vaciar carrito
            val clearCartButton = Button(this@CartActivity).apply {
                text = "🗑️ VACIAR CARRITO"
                textSize = 14f
                setBackgroundColor(Color.parseColor("#F44336"))
                setTextColor(Color.WHITE)
                setPadding(24, 16, 24, 16)

                setOnClickListener {
                    clearCart()
                }
            }
            addView(clearCartButton)
        }
    }

    // ===============================
    // MÉTODOS DE CARRITO FUNCIONAL
    // ===============================

    private fun updateItemQuantity(cartItem: CartItem, newQuantity: Int) {
        try {
            if (newQuantity <= 0) {
                // Remover item del carrito
                cartItems.removeIf { it.productId == cartItem.productId }
                showToast("🗑️ ${cartItem.productName} removido del carrito")
            } else {
                // Actualizar cantidad
                val index = cartItems.indexOfFirst { it.productId == cartItem.productId }
                if (index != -1) {
                    cartItems[index] = cartItem.copy(quantity = newQuantity)
                    showToast("✅ Cantidad actualizada: ${cartItem.productName} (${newQuantity})")
                }
            }

            // Actualizar la vista
            if (cartItems.isEmpty()) {
                recreate()
            } else {
                updateCartDisplay()
                updateTotalDisplay()
            }

            Log.d("CartActivity", "Cantidad actualizada. Carrito: ${cartItems.size} items")

        } catch (e: Exception) {
            Log.e("CartActivity", "Error actualizando cantidad", e)
            showToast("⚠️ Error actualizando producto")
        }
    }

    private fun clearCart() {
        try {
            cartItems.clear()
            showToast("🗑️ Carrito vaciado")
            recreate()

        } catch (e: Exception) {
            Log.e("CartActivity", "Error vaciando carrito", e)
            showToast("⚠️ Error vaciando carrito")
        }
    }

    private fun processPurchase() {
        try {
            val total = calculateTotal()
            val itemCount = cartItems.sumOf { it.quantity }

            showToast("🎉 ¡COMPRA EXITOSA!")

            // Simular proceso de compra exitoso
            val purchaseResult = "✅ Compra realizada exitosamente\\n\\nTotal: $$total"

            // Limpiar carrito después de la compra
            cartItems.clear()

            // Crear vista de confirmación
            createPurchaseConfirmationUI(purchaseResult)

            Log.d("CartActivity", "Compra procesada exitosamente. Total: $$total")

        } catch (e: Exception) {
            Log.e("CartActivity", "Error procesando compra", e)
            showToast("⚠️ Error procesando compra")
        }
    }

    private fun createPurchaseConfirmationUI(message: String) {
        try {
            // Crear vista de confirmación
            val confirmationLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.parseColor("#121212"))
                setPadding(64, 100, 64, 100)
                gravity = Gravity.CENTER
            }

            // Icono de éxito
            val successIcon = TextView(this).apply {
                text = "🎉"
                textSize = 80f
                gravity = Gravity.CENTER
                setPadding(0, 0, 0, 32)
            }
            confirmationLayout.addView(successIcon)

            // Mensaje de confirmación
            val confirmationMessage = TextView(this).apply {
                text = message
                textSize = 16f
                setTextColor(Color.parseColor("#E0E0E0"))
                gravity = Gravity.CENTER
                setPadding(0, 0, 0, 40)
            }
            confirmationLayout.addView(confirmationMessage)

            // Botón continuar
            val continueButton = Button(this).apply {
                text = "🏪 VOLVER A LA TIENDA"
                textSize = 16f
                setBackgroundColor(Color.parseColor("#4CAF50"))
                setTextColor(Color.WHITE)
                setPadding(40, 20, 40, 20)

                setOnClickListener {
                    finishWithResult()
                }
            }
            confirmationLayout.addView(continueButton)

            setContentView(confirmationLayout)

        } catch (e: Exception) {
            Log.e("CartActivity", "Error creando confirmación", e)
            finishWithResult()
        }
    }

    private fun calculateSubtotal(): Double {
        return try {
            cartItems.sumOf { it.totalPrice }
        } catch (e: Exception) {
            0.0
        }
    }

    private fun calculateTax(): Double {
        return try {
            calculateSubtotal() * 0.10
        } catch (e: Exception) {
            0.0
        }
    }

    private fun calculateTotal(): Double {
        return try {
            calculateSubtotal() + calculateTax()
        } catch (e: Exception) {
            0.0
        }
    }

    private fun updateTotalDisplay() {
        try {
            if (::totalAmountText.isInitialized) {
                totalAmountText.text = "💰 TOTAL: $${calculateTotal()}"
            }
        } catch (e: Exception) {
            Log.e("CartActivity", "Error actualizando total", e)
        }
    }

    private fun finishWithResult() {
        try {
            val resultIntent = Intent().apply {
                putParcelableArrayListExtra("updated_cart_items", ArrayList(cartItems))
                putExtra("total_amount", calculateTotal())
            }
            setResult(RESULT_OK, resultIntent)
            finish()

        } catch (e: Exception) {
            Log.e("CartActivity", "Error finalizando actividad", e)
            finish()
        }
    }

    private fun createErrorUI(error: Exception) {
        try {
            Log.e("CartActivity", "Creando UI de error por: ${error.message}")

            val errorLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.parseColor("#121212"))
                setPadding(64, 100, 64, 100)
                gravity = Gravity.CENTER
            }

            val errorMessage = TextView(this).apply {
                text = "Error en el carrito"
                textSize = 16f
                setTextColor(Color.parseColor("#E0E0E0"))
                gravity = Gravity.CENTER
                setPadding(0, 0, 0, 40)
            }
            errorLayout.addView(errorMessage)

            val backButton = Button(this).apply {
                text = "← VOLVER"
                textSize = 14f
                setBackgroundColor(Color.parseColor("#666666"))
                setTextColor(Color.WHITE)
                setPadding(24, 12, 24, 12)

                setOnClickListener {
                    finish()
                }
            }
            errorLayout.addView(backButton)

            setContentView(errorLayout)

        } catch (e: Exception) {
            Log.e("CartActivity", "Error crítico", e)
            finish()
        }
    }

    private fun showToast(message: String) {
        try {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("CartActivity", "Error mostrando toast: $message", e)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishWithResult()
    }
}
