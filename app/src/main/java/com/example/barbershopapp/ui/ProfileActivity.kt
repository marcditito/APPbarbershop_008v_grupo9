package com.example.barbershopapp.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.view.Gravity
import android.view.View
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.utils.AnimationUtils
import com.example.barbershopapp.utils.VisualFeedbackUtils

/**
 * Activity de Perfil - Información del usuario
 */
class ProfileActivity : AppCompatActivity() {

    // Datos simulados del perfil
    private var firstName = "Carlos"
    private var lastName = "Hernández"
    private var email = "carlos.hernandez@email.com"
    private var phone = "+34 612 345 678"
    private var address = "Calle Principal 123, Madrid"
    private var isEditing = false

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

            // Avatar
            contentLayout.addView(createAvatarSection())

            // Divider
            contentLayout.addView(createDivider())

            // Información personal
            contentLayout.addView(createProfileInfoSection())

            // Divider
            contentLayout.addView(createDivider())

            // Estadísticas
            contentLayout.addView(createStatsSection())

            // Divider
            contentLayout.addView(createDivider())

            // Botones de acción
            contentLayout.addView(createActionButtons())

            scrollView.addView(contentLayout)
            mainLayout.addView(scrollView)

            // Bottom navigation
            mainLayout.addView(createBottomNav())

            setContentView(mainLayout)

            Log.d("ProfileActivity", "✅ Profile Activity iniciada exitosamente")

        } catch (e: Exception) {
            Log.e("ProfileActivity", "Error creando Profile Activity", e)
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
            val backBtn = Button(this@ProfileActivity).apply {
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
            val title = TextView(this@ProfileActivity).apply {
                text = "👤 PERFIL"
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

    private fun createAvatarSection(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#1E1E1E"))
            setPadding(16, 24, 16, 24)
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 16
            }

            // Avatar (emoji grande)
            val avatar = TextView(this@ProfileActivity).apply {
                text = "👨‍💼"
                textSize = 80f
                gravity = Gravity.CENTER
            }
            addView(avatar)

            // Nombre completo
            val nameText = TextView(this@ProfileActivity).apply {
                text = "$firstName $lastName"
                textSize = 22f
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                setPadding(0, 12, 0, 0)
            }
            addView(nameText)

            // Estado
            val statusText = TextView(this@ProfileActivity).apply {
                text = "✅ Cliente Activo"
                textSize = 14f
                setTextColor(Color.parseColor("#4CAF50"))
                gravity = Gravity.CENTER
                setPadding(0, 4, 0, 0)
            }
            addView(statusText)
        }
    }

    private fun createProfileInfoSection(): LinearLayout {
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

            val title = TextView(this@ProfileActivity).apply {
                text = "📋 INFORMACIÓN PERSONAL"
                textSize = 16f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 12)
            }
            addView(title)

            // Campos de información
            addView(createInfoCard("👤 Nombre", firstName))
            addView(createInfoCard("👤 Apellido", lastName))
            addView(createInfoCard("📧 Email", email))
            addView(createInfoCard("📱 Teléfono", phone))
            addView(createInfoCard("📍 Dirección", address))
        }
    }

    private fun createInfoCard(label: String, value: String): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#2C2C2C"))
            setPadding(12, 12, 12, 12)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 8
            }

            val labelText = TextView(this@ProfileActivity).apply {
                text = label
                textSize = 12f
                setTextColor(Color.parseColor("#999999"))
            }
            addView(labelText)

            val valueText = TextView(this@ProfileActivity).apply {
                text = value
                textSize = 14f
                setTextColor(Color.parseColor("#E0E0E0"))
                setPadding(0, 4, 0, 0)
            }
            addView(valueText)
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

            val title = TextView(this@ProfileActivity).apply {
                text = "📊 ESTADÍSTICAS"
                textSize = 16f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 12)
            }
            addView(title)

            addView(createStatItem("🛒 Compras Totales", "47"))
            addView(createStatItem("💰 Gasto Total", "$3,245.80"))
            addView(createStatItem("⭐ Calificación", "4.9/5.0"))
            addView(createStatItem("📅 Miembro desde", "Enero 2024"))
        }
    }

    private fun createStatItem(label: String, value: String): LinearLayout {
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

            val labelText = TextView(this@ProfileActivity).apply {
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

            val valueText = TextView(this@ProfileActivity).apply {
                text = value
                textSize = 14f
                setTextColor(Color.parseColor("#4CAF50"))
            }
            addView(valueText)
        }
    }

    private fun createActionButtons(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Botón editar perfil
            val editBtn = Button(this@ProfileActivity).apply {
                text = "✏️ EDITAR PERFIL"
                textSize = 16f
                setBackgroundColor(Color.parseColor("#2196F3"))
                setTextColor(Color.WHITE)
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 8
                }

                setOnClickListener {
                    AnimationUtils.animateClickScale(this)
                    VisualFeedbackUtils.burstAnimation(this, 400)
                    VisualFeedbackUtils.showVisualToast(this@ProfileActivity, "Función próximamente", "🔜")
                }
            }
            addView(editBtn)

            // Botón cambiar contraseña
            val changePassBtn = Button(this@ProfileActivity).apply {
                text = "🔐 CAMBIAR CONTRASEÑA"
                textSize = 16f
                setBackgroundColor(Color.parseColor("#FF9800"))
                setTextColor(Color.WHITE)
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 8
                }

                setOnClickListener {
                    AnimationUtils.animateClickScale(this)
                    VisualFeedbackUtils.burstAnimation(this, 400)
                    VisualFeedbackUtils.showVisualToast(this@ProfileActivity, "Función próximamente", "🔜")
                }
            }
            addView(changePassBtn)

            // Botón cerrar sesión
            val logoutBtn = Button(this@ProfileActivity).apply {
                text = "🚪 CERRAR SESIÓN"
                textSize = 16f
                setBackgroundColor(Color.parseColor("#F44336"))
                setTextColor(Color.WHITE)
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                setOnClickListener {
                    AnimationUtils.animateClickScale(this)
                    VisualFeedbackUtils.burstAnimation(this, 400)
                    VisualFeedbackUtils.confirmationFeedback(this@ProfileActivity, true)
                    VisualFeedbackUtils.showVisualToast(this@ProfileActivity, "Sesión cerrada", "👋")

                    // Ir a Login
                    val intent = Intent(this@ProfileActivity, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }
            }
            addView(logoutBtn)
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
                "💰 Ventas" to { navigateToSales() }
            )

            buttons.forEach { (text, action) ->
                val btn = Button(this@ProfileActivity).apply {
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

    private fun navigateToSales() {
        val intent = Intent(this, SalesActivity::class.java)
        startActivity(intent)
    }
}

