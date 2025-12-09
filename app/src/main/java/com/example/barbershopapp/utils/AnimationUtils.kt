package com.example.barbershopapp.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation

/**
 * Utilidades para animaciones simples pero efectivas.
 * Se integra sin afectar el código existente.
 */
object AnimationUtils {

    /**
     * Animación de escala al hacer clic (presionar botón)
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animateClickScale(view: View, duration: Long = 150) {
        val scaleDown = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f)
        scaleDown.duration = duration / 2
        scaleDown.interpolator = AccelerateDecelerateInterpolator()

        val scaleUp = ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1f)
        scaleUp.duration = duration / 2
        scaleUp.interpolator = AccelerateDecelerateInterpolator()

        scaleDown.start()
        scaleDown.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                scaleUp.start()
            }
        })

        // Mismo para Y
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f)
        scaleDownY.duration = duration / 2
        scaleDownY.interpolator = AccelerateDecelerateInterpolator()

        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1f)
        scaleUpY.duration = duration / 2
        scaleUpY.interpolator = AccelerateDecelerateInterpolator()

        scaleDownY.start()
        scaleDownY.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                scaleUpY.start()
            }
        })
    }

    /**
     * Animación de aparición con fade in
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animateFadeIn(view: View, duration: Long = 500) {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = duration
        view.startAnimation(fadeIn)
    }

    /**
     * Animación de desaparición con fade out
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animateFadeOut(view: View, duration: Long = 500) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = duration
        view.startAnimation(fadeOut)
    }

    /**
     * Animación de traslación deslizante (entrada desde arriba)
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animateSlideInFromTop(view: View, duration: Long = 400) {
        val slideIn = TranslateAnimation(0f, 0f, -view.height.toFloat(), 0f)
        slideIn.duration = duration
        slideIn.interpolator = AccelerateDecelerateInterpolator()
        view.startAnimation(slideIn)
    }

    /**
     * Animación de traslación deslizante (entrada desde abajo)
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animateSlideInFromBottom(view: View, duration: Long = 400) {
        val slideIn = TranslateAnimation(0f, 0f, view.height.toFloat(), 0f)
        slideIn.duration = duration
        slideIn.interpolator = AccelerateDecelerateInterpolator()
        view.startAnimation(slideIn)
    }

    /**
     * Animación de rotación (útil para loading spinners)
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animateRotation(view: View, duration: Long = 1000) {
        val rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
        rotate.duration = duration
        rotate.repeatCount = ObjectAnimator.INFINITE
        rotate.interpolator = AccelerateDecelerateInterpolator()
        rotate.start()
    }

    /**
     * Animación de pulso (expansión y contracción)
     * @param view Vista a animar
     * @param duration Duración en milisegundos
     */
    fun animatePulse(view: View, duration: Long = 500) {
        val scaleUp = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.1f)
        scaleUp.duration = duration / 2
        scaleUp.interpolator = AccelerateDecelerateInterpolator()

        val scaleDown = ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1f)
        scaleDown.duration = duration / 2
        scaleDown.interpolator = AccelerateDecelerateInterpolator()

        scaleUp.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                scaleDown.start()
            }
        })

        scaleUp.start()

        // Mismo para Y
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.1f)
        scaleUpY.duration = duration / 2
        scaleUpY.interpolator = AccelerateDecelerateInterpolator()

        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1f)
        scaleDownY.duration = duration / 2
        scaleDownY.interpolator = AccelerateDecelerateInterpolator()

        scaleUpY.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                scaleDownY.start()
            }
        })

        scaleUpY.start()
    }

    /**
     * Animación de rebote
     * @param view Vista a animar
     * @param bounceHeight Altura del rebote en píxeles
     */
    fun animateBounce(view: View, bounceHeight: Float = 50f, duration: Long = 800) {
        val animator = ObjectAnimator.ofFloat(view, "translationY", 0f, -bounceHeight, 0f)
        animator.duration = duration
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }
}

