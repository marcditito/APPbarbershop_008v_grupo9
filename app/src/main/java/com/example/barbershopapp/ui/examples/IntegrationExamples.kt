package com.example.barbershopapp.ui.examples

import android.content.Context
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.barbershopapp.utils.AnimationUtils
import com.example.barbershopapp.utils.NativeDeviceUtils
import com.example.barbershopapp.utils.NavigationManager


object IntegrationExamples {

    private val TAG = "IntegrationExamples"

    /**
     * Ejemplo 1: Animar un botón al hacer clic + vibración
     *
     * Usa esto en tus Button listeners:
     * ```
     * miButton.setOnClickListener {
     *     IntegrationExamples.addButtonClickAnimation(miButton, context)
     * }
     * ```
     */
    fun addButtonClickAnimation(button: Button, context: Context) {
        // Vibración táctil pequeña
        NativeDeviceUtils.vibrationClick(context)

        // Animación de escala
        AnimationUtils.animateClickScale(button, duration = 150)

        Log.d(TAG, "✅ Click animado ejecutado")
    }

    /**
     * Ejemplo 2: Entrada animada de vista con fade in
     *
     * Usa esto cuando necesites mostrar una vista con animación:
     * ```
     * IntegrationExamples.showViewWithAnimation(myView)
     * ```
     */
    fun showViewWithAnimation(view: View, duration: Long = 500) {
        view.alpha = 0f
        view.visibility = View.VISIBLE
        AnimationUtils.animateFadeIn(view, duration)
        Log.d(TAG, "✅ Vista mostrada con animación")
    }

    /**
     * Ejemplo 3: Salida animada de vista con fade out
     *
     * Usa esto cuando necesites ocular una vista:
     * ```
     * IntegrationExamples.hideViewWithAnimation(myView)
     * ```
     */
    fun hideViewWithAnimation(view: View, duration: Long = 500) {
        AnimationUtils.animateFadeOut(view, duration)

        Handler(Looper.getMainLooper()).postDelayed({
            view.visibility = View.GONE
        }, duration)

        Log.d(TAG, "✅ Vista ocultada con animación")
    }

    /**
     * Ejemplo 4: Efecto de carga con rotación
     *
     * Usa esto para indicar que algo está cargando:
     * ```
     * val loadingView = findViewById<View>(R.id.loading_spinner)
     * IntegrationExamples.startLoadingAnimation(loadingView)
     * ```
     */
    fun startLoadingAnimation(loadingView: View) {
        loadingView.visibility = View.VISIBLE
        AnimationUtils.animateRotation(loadingView)
        Log.d(TAG, "✅ Animación de carga iniciada")
    }

    /**
     * Ejemplo 5: Notificación de éxito con vibración y animación
     *
     * Úsalo cuando la operación sea exitosa:
     * ```
     * IntegrationExamples.showSuccessNotification(successButton, context)
     * ```
     */
    fun showSuccessNotification(button: Button, context: Context) {
        // Vibración de éxito (patrón especial)
        NativeDeviceUtils.vibrationSuccess(context)

        // Animación de pulso
        AnimationUtils.animatePulse(button)

        Log.d(TAG, "✅ Notificación de éxito mostrada")
    }

    /**
     * Ejemplo 6: Notificación de error con vibración y animación
     *
     * Úsalo cuando haya un error:
     * ```
     * IntegrationExamples.showErrorNotification(errorButton, context)
     * ```
     */
    fun showErrorNotification(button: Button, context: Context) {
        // Vibración de error (patrón de alerta)
        NativeDeviceUtils.vibrationError(context)

        // Animación de rebote (indica error)
        AnimationUtils.animateBounce(button)

        Log.d(TAG, "⚠️ Notificación de error mostrada")
    }

    /**
     * Ejemplo 7: Cambiar entre vistas con transición suave
     *
     * Usa esto para mostrar/ocultar vistas con transición:
     * ```
     * IntegrationExamples.switchViews(viewActual, viewNueva)
     * ```
     */
    fun switchViews(viewToHide: View, viewToShow: View, duration: Long = 500) {
        // Fade out de la vista actual
        AnimationUtils.animateFadeOut(viewToHide, duration / 2)

        // Fade in de la nueva vista
        Handler(Looper.getMainLooper()).postDelayed({
            viewToHide.visibility = View.GONE
            viewToShow.visibility = View.VISIBLE
            AnimationUtils.animateFadeIn(viewToShow, duration / 2)
        }, duration / 2)

        Log.d(TAG, "✅ Transición entre vistas completada")
    }

    /**
     * Ejemplo 8: Navegación con animación
     *
     * Úsalo en tus botones para navegar:
     * ```
     * miButton.setOnClickListener {
     *     IntegrationExamples.navigateWithAnimation(this, targetActivity)
     * }
     * ```
     */
    fun navigateWithAnimation(
        currentActivity: android.app.Activity,
        onNavigate: () -> Unit
    ) {
        // Vibración de éxito
        NativeDeviceUtils.vibrationSuccess(currentActivity)

        // Animación de fade out
        AnimationUtils.animateFadeOut(currentActivity.window.decorView, 300)

        Handler(Looper.getMainLooper()).postDelayed({
            onNavigate()
        }, 300)

        Log.d(TAG, "✅ Navegación animada ejecutada")
    }

    /**
     * Ejemplo 9: Patrón de vibración personalizado
     *
     * Crea patrones únicos de vibración:
     * ```
     * val miPatron = longArrayOf(0, 100, 50, 100, 50, 100)
     * IntegrationExamples.playCustomVibrationPattern(miPatron, context)
     * ```
     */
    fun playCustomVibrationPattern(pattern: LongArray, context: Context) {
        NativeDeviceUtils.vibratePattern(context, pattern)
        Log.d(TAG, "✅ Patrón de vibración personalizado ejecutado")
    }

    /**
     * Ejemplo 10: Entrada animada de lista o contenido
     *
     * Úsalo para animar el ingreso de contenido:
     * ```
     * IntegrationExamples.animateContentEntry(contentView, fromBottom = true)
     * ```
     */
    fun animateContentEntry(contentView: View, fromBottom: Boolean = true) {
        if (fromBottom) {
            AnimationUtils.animateSlideInFromBottom(contentView)
        } else {
            AnimationUtils.animateSlideInFromTop(contentView)
        }
        Log.d(TAG, "✅ Entrada de contenido animada")
    }

    /**
     * Ejemplo 11: Dialog o modal con animación
     *
     * Muestra un modal con efecto de aparición:
     * ```
     * IntegrationExamples.showModalWithAnimation(dialogView)
     * ```
     */
    fun showModalWithAnimation(modalView: View) {
        modalView.visibility = View.VISIBLE
        modalView.alpha = 0f
        AnimationUtils.animateClickScale(modalView, 400)
        AnimationUtils.animateFadeIn(modalView, 400)
        Log.d(TAG, "✅ Modal mostrado con animación")
    }

    /**
     * Ejemplo 12: Texto que rebota (para llamar atención)
     *
     * Úsalo para resaltar un mensaje importante:
     * ```
     * IntegrationExamples.bounceText(importantTextView)
     * ```
     */
    fun bounceText(textView: TextView) {
        AnimationUtils.animateBounce(textView, bounceHeight = 30f, duration = 600)
        Log.d(TAG, "✅ Texto rebotando para llamar atención")
    }

    /**
     * Ejemplo 13: Flujo completo: Action → Vibración → Animación → Navegación
     *
     * Encadena todo para una experiencia completa:
     * ```
     * miButton.setOnClickListener {
     *     IntegrationExamples.completeActionFlow(
     *         button = miButton,
     *         context = context,
     *         activity = this,
     *         onSuccess = {
     *             NavigationManager.goToMainActivity(this)
     *         }
     *     )
     * }
     * ```
     */
    fun completeActionFlow(
        button: Button,
        context: Context,
        activity: android.app.Activity,
        onSuccess: () -> Unit
    ) {
        // 1. Vibración inicial
        NativeDeviceUtils.vibrationClick(context)

        // 2. Animación del botón
        AnimationUtils.animateClickScale(button)

        // 3. Mostrar indicador de carga
        button.isEnabled = false
        button.text = "Procesando..."

        // 4. Simular operación (reemplazar con tu lógica real)
        Handler(Looper.getMainLooper()).postDelayed({
            // 5. Éxito
            NativeDeviceUtils.vibrationSuccess(context)
            AnimationUtils.animatePulse(button)

            // 6. Navegar después de éxito
            Handler(Looper.getMainLooper()).postDelayed({
                onSuccess()
            }, 500)

        }, 1500)

        Log.d(TAG, "✅ Flujo de acción completo iniciado")
    }

    /**
     * Ejemplo 14: Animación de "agregar al carrito" visual
     *
     * Efecto visual cuando se agrega un producto:
     * ```
     * IntegrationExamples.addToCartAnimation(addButton, context)
     * ```
     */
    fun addToCartAnimation(button: Button, context: Context) {
        // Vibración
        NativeDeviceUtils.vibrationClick(context)

        // Animación de escala (como si se agregara)
        AnimationUtils.animateClickScale(button, 200)

        // Cambiar texto
        val originalText = button.text
        button.text = "✓ Agregado"

        // Volver al texto original
        Handler(Looper.getMainLooper()).postDelayed({
            button.text = originalText
        }, 1500)

        Log.d(TAG, "✅ Animación de 'agregar al carrito' ejecutada")
    }

    /**
     * Ejemplo 15: Confirmación visual + sonora + háptica
     *
     * Retroalimentación multisensorial completa:
     * ```
     * IntegrationExamples.fullFeedback(confirmButton, context)
     * ```
     */
    fun fullFeedback(button: Button, context: Context) {
        // Retroalimentación háptica
        NativeDeviceUtils.vibrationSuccess(context)

        // Retroalimentación visual
        AnimationUtils.animatePulse(button, 600)

        // Cambio de apariencia
        button.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({
            button.isEnabled = true
        }, 1000)

        Log.d(TAG, "✅ Retroalimentación multisensorial completada")
    }
}

