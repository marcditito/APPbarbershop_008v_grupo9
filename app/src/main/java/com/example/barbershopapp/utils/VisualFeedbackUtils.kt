package com.example.barbershopapp.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.barbershopapp.R

/**
 * Visual Feedback nativo de Android
 * Muestra feedback visual cuando se activan eventos (sensores, botones, etc)
 * Perfecto para emulador donde no se siente vibración
 */
object VisualFeedbackUtils {

    private const val TAG = "VisualFeedback"

    /**
     * Flash visual - Parpadeo de pantalla
     * @param activity Activity actual
     * @param color Color del flash
     * @param duration Duración en ms
     */
    fun flashScreen(activity: Activity, color: Int = Color.WHITE, duration: Long = 200) {
        try {
            val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
            val flashView = View(activity).apply {
                setBackgroundColor(color)
                alpha = 0.5f
            }

            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            val container = rootView as? FrameLayout
            container?.addView(flashView, params)

            flashView.animate()
                .alpha(0f)
                .setDuration(duration)
                .withEndAction {
                    container?.removeView(flashView)
                }
                .start()
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en flash screen", e)
        }
    }

    /**
     * Pulso de color en una vista
     * @param view Vista que pulsará
     * @param color Color del pulso
     * @param duration Duración total en ms
     */
    fun pulseView(view: View, color: Int = Color.YELLOW, duration: Long = 500) {
        try {
            val originalColor = when (view.background) {
                is android.graphics.drawable.ColorDrawable ->
                    (view.background as android.graphics.drawable.ColorDrawable).color
                else -> Color.TRANSPARENT
            }

            val animation = ScaleAnimation(
                1f, 1.1f, 1f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                this.duration = duration
                repeatCount = 0
            }

            view.startAnimation(animation)

            // Cambiar color temporalmente
            view.setBackgroundColor(color)
            view.postDelayed({
                try {
                    view.setBackgroundColor(originalColor)
                } catch (e: Exception) {
                    android.util.Log.e(TAG, "Error restaurando color", e)
                }
            }, duration)
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en pulse view", e)
        }
    }

    /**
     * Toast personalizado con ícono/emoji
     * @param activity Activity actual
     * @param message Mensaje a mostrar
     * @param emoji Emoji opcional
     */
    fun showVisualToast(activity: Activity, message: String, emoji: String = "✅") {
        try {
            val fullMessage = "$emoji $message"
            Toast.makeText(activity, fullMessage, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en toast visual", e)
        }
    }

    /**
     * Animación de estallido/explosión
     * @param view Vista que "explotará"
     * @param duration Duración en ms
     */
    fun burstAnimation(view: View, duration: Long = 600) {
        try {
            val scaleAnim = ScaleAnimation(
                1f, 1.2f,
                1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                this.duration = duration / 2
            }

            view.startAnimation(scaleAnim)

            view.postDelayed({
                try {
                    val shrinkAnim = ScaleAnimation(
                        1.2f, 1f,
                        1.2f, 1f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    ).apply {
                        this.duration = duration / 2
                    }
                    view.startAnimation(shrinkAnim)
                } catch (e: Exception) {
                    android.util.Log.e(TAG, "Error en shrink", e)
                }
            }, duration / 2)
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en burst animation", e)
        }
    }

    /**
     * Indicador visual de sensor detectado
     * Muestra un ícono/badge que aparece y desaparece
     * @param activity Activity actual
     * @param sensorName Nombre del sensor
     */
    fun showSensorDetectedIndicator(activity: Activity, sensorName: String) {
        try {
            val emoji = when (sensorName.lowercase()) {
                "acelerómetro", "accelerometer" -> "📱"
                "proximidad", "proximity" -> "📍"
                "luz", "light" -> "💡"
                "sacudida", "shake" -> "🎉"
                else -> "⚡"
            }

            showVisualToast(activity, "¡$sensorName detectado!", emoji)

            // Flash visual adicional
            flashScreen(activity, Color.YELLOW, 100)
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en sensor indicator", e)
        }
    }

    /**
     * Efecto de ripple (ondas)
     * @param view Vista que hará ripple
     * @param duration Duración en ms
     */
    fun rippleEffect(view: View, duration: Long = 400) {
        try {
            // Crear múltiples pulsos
            for (i in 0..2) {
                view.postDelayed({
                    try {
                        val scale = ScaleAnimation(
                            1f, 1.3f,
                            1f, 1.3f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f
                        ).apply {
                            this.duration = duration
                        }
                        view.startAnimation(scale)
                    } catch (e: Exception) {
                        android.util.Log.e(TAG, "Error en ripple", e)
                    }
                }, i * 100L)
            }
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en ripple effect", e)
        }
    }

    /**
     * Efecto de brillo
     * @param view Vista que brillará
     * @param duration Duración en ms
     */
    fun glowEffect(view: View, duration: Long = 500) {
        try {
            val originalAlpha = view.alpha
            view.animate()
                .alpha(0.7f)
                .setDuration(duration / 2)
                .withEndAction {
                    view.animate()
                        .alpha(originalAlpha)
                        .setDuration(duration / 2)
                        .start()
                }
                .start()
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en glow effect", e)
        }
    }

    /**
     * Combinación: Flash + Toast + Animación
     * Feedback visual completo para evento importante
     * @param activity Activity actual
     * @param message Mensaje a mostrar
     * @param view Vista a animar (opcional)
     */
    fun fullVisualFeedback(activity: Activity, message: String, view: View? = null) {
        try {
            // 1. Flash
            flashScreen(activity, Color.CYAN, 150)

            // 2. Toast
            showVisualToast(activity, message, "✨")

            // 3. Animar vista si se proporciona
            view?.let {
                burstAnimation(it, 400)
            }
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en full feedback", e)
        }
    }

    /**
     * Confirmación visual de acción
     * @param activity Activity actual
     * @param success true = éxito (verde), false = error (rojo)
     */
    fun confirmationFeedback(activity: Activity, success: Boolean) {
        try {
            val color = if (success) Color.GREEN else Color.RED
            val emoji = if (success) "✅" else "❌"
            val message = if (success) "¡Acción exitosa!" else "¡Error detectado!"

            flashScreen(activity, color, 200)
            showVisualToast(activity, message, emoji)
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en confirmation feedback", e)
        }
    }

    /**
     * Efecto de descarga/energía
     * Múltiples flashes en secuencia
     * @param activity Activity actual
     */
    fun chargeEffect(activity: Activity) {
        try {
            for (i in 0..3) {
                activity.window.decorView.postDelayed({
                    try {
                        val color = if (i % 2 == 0) Color.YELLOW else Color.CYAN
                        flashScreen(activity, color, 100)
                    } catch (e: Exception) {
                        android.util.Log.e(TAG, "Error en charge effect", e)
                    }
                }, i * 150L)
            }
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error en charge effect", e)
        }
    }
}

