package com.example.barbershopapp.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.view.Gravity
import android.view.View
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.model.CartItem
import com.example.barbershopapp.utils.AnimationUtils
import com.example.barbershopapp.utils.NativeAndroidUtils
import com.example.barbershopapp.utils.VisualFeedbackUtils

/**
 * Activity de Ventas - Muestra ventas simuladas del día/semana/mes
 */
class SalesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        try {
            val mainLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.parseColor("#121212"))
                setPadding(16, 32, 16, 16)
            }

            // Toolbar
            val toolbar = createToolbar()
            mainLayout.addView(toolbar)

            // ScrollView para contenido
            val scrollView = ScrollView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1f
                )
            }

            val contentLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            // Sección de estadísticas
            contentLayout.addView(createStatsSection())

            // Divider
            contentLayout.addView(createDivider())

            // Ventas del día
            contentLayout.addView(createSalesSection("📅 VENTAS DEL DÍA (HOY)", getSalesOfDay()))

            // Divider
            contentLayout.addView(createDivider())

            // Ventas de la semana
            contentLayout.addView(createSalesSection("📊 TOP VENTAS SEMANA", getWeeklySales()))

            // Divider
            contentLayout.addView(createDivider())

            // Mejores productos
            contentLayout.addView(createSalesSection("⭐ PRODUCTOS MÁS VENDIDOS", getTopProducts()))

            scrollView.addView(contentLayout)
            mainLayout.addView(scrollView)

            // Bottom navigation
            mainLayout.addView(createBottomNav())

            setContentView(mainLayout)

            Log.d("SalesActivity", "✅ Sales Activity iniciada exitosamente")

        } catch (e: Exception) {
            Log.e("SalesActivity", "Error creando Sales Activity", e)
            val errorText = TextView(this).apply {
                text = "Error: ${e.message}"
                setTextColor(Color.RED)
            }
            setContentView(errorText)
        }
    }

    private fun createToolbar(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Color.parseColor("#1F1F1F"))
            setPadding(16, 16, 16, 16)
            gravity = Gravity.CENTER_VERTICAL

            // Botón atrás
            val backBtn = Button(this@SalesActivity).apply {
                text = "← Atrás"
                textSize = 14f
                setBackgroundColor(Color.parseColor("#333333"))
                setTextColor(Color.WHITE)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginEnd = 16
                }

                setOnClickListener {
                    AnimationUtils.animateClickScale(this)
                    VisualFeedbackUtils.glowEffect(this, 300)
                    finish()
                }
            }
            addView(backBtn)

            // Título
            val title = TextView(this@SalesActivity).apply {
                text = "💰 VENTAS"
                textSize = 24f
                setTextColor(Color.WHITE)
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }
            addView(title)
        }
    }

    private fun createStatsSection(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#1E1E1E"))
            setPadding(16, 16, 16, 16)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 16
            }

            val title = TextView(this@SalesActivity).apply {
                text = "📈 ESTADÍSTICAS"
                textSize = 18f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 12)
            }
            addView(title)

            // Total ventas
            addView(createStatCard("💵 Total Ventas Hoy", "$1,245.50"))
            addView(createStatCard("🛒 Órdenes Completadas", "8"))
            addView(createStatCard("📦 Productos Vendidos", "23"))
            addView(createStatCard("⭐ Calificación Promedio", "4.8/5.0"))
        }
    }

    private fun createStatCard(label: String, value: String): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Color.parseColor("#2C2C2C"))
            setPadding(12, 12, 12, 12)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 8
            }
            gravity = Gravity.CENTER_VERTICAL

            val labelText = TextView(this@SalesActivity).apply {
                text = label
                textSize = 14f
                setTextColor(Color.parseColor("#B0B0B0"))
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }
            addView(labelText)

            val valueText = TextView(this@SalesActivity).apply {
                text = value
                textSize = 16f
                setTextColor(Color.parseColor("#4CAF50"))
            }
            addView(valueText)
        }
    }

    private fun createSalesSection(title: String, sales: List<Pair<String, String>>): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 16
            }

            val titleText = TextView(this@SalesActivity).apply {
                text = title
                textSize = 16f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 12)
            }
            addView(titleText)

            sales.forEach { (product, value) ->
                val saleCard = LinearLayout(this@SalesActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setBackgroundColor(Color.parseColor("#2C2C2C"))
                    setPadding(12, 12, 12, 12)
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        bottomMargin = 8
                    }
                    gravity = Gravity.CENTER_VERTICAL
                }

                val productText = TextView(this@SalesActivity).apply {
                    text = product
                    textSize = 14f
                    setTextColor(Color.parseColor("#E0E0E0"))
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                }
                saleCard.addView(productText)

                val valueText = TextView(this@SalesActivity).apply {
                    text = value
                    textSize = 14f
                    setTextColor(Color.parseColor("#FF9800"))
                }
                saleCard.addView(valueText)

                addView(saleCard)
            }
        }
    }

    private fun createDivider(): View {
        return View(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
            ).apply {
                topMargin = 12
                bottomMargin = 12
            }
            setBackgroundColor(Color.parseColor("#333333"))
        }
    }

    private fun createBottomNav(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Color.parseColor("#1F1F1F"))
            setPadding(8, 12, 8, 12)

            val buttons = listOf(
                "🏠 Inicio" to { navigateToHome() },
                "👤 Perfil" to { navigateToProfile() }
            )

            buttons.forEach { (text, action) ->
                val btn = Button(this@SalesActivity).apply {
                    this.text = text
                    textSize = 12f
                    setBackgroundColor(Color.parseColor("#333333"))
                    setTextColor(Color.WHITE)
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    ).apply {
                        marginStart = 4
                        marginEnd = 4
                    }
                    setOnClickListener {
                        AnimationUtils.animateClickScale(this)
                        VisualFeedbackUtils.glowEffect(this, 300)
                        action()
                    }
                }
                addView(btn)
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        finish()
    }

    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    // Datos de ventas simuladas
    private fun getSalesOfDay(): List<Pair<String, String>> {
        return listOf(
            "🧴 Champú Premium x 3" to "$47.97",
            "🧔 Aceite de Barba x 2" to "$25.00",
            "✨ Gel Fijador x 4" to "$35.96",
            "💫 Pomada Matt x 1" to "$10.99",
            "❄️ Aftershave Mentol x 2" to "$29.00",
            "🎯 Cera para Bigote x 3" to "$29.97"
        )
    }

    private fun getWeeklySales(): List<Pair<String, String>> {
        return listOf(
            "🧴 Champú Premium" to "$234.15 (15 unid)",
            "🧔 Aceite de Barba" to "$156.25 (12 unid)",
            "✨ Gel Fijador" to "$143.60 (16 unid)",
            "💫 Pomada Matt" to "$109.90 (10 unid)",
            "❄️ Aftershave Mentol" to "$145.00 (10 unid)"
        )
    }

    private fun getTopProducts(): List<Pair<String, String>> {
        return listOf(
            "🥇 Champú Premium" to "23 ventas",
            "🥈 Gel Fijador" to "19 ventas",
            "🥉 Aceite de Barba" to "18 ventas",
            "4️⃣ Aftershave Mentol" to "15 ventas",
            "5️⃣ Pomada Matt" to "14 ventas"
        )
    }
}

