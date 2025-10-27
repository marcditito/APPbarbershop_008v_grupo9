package com.example.barbershopapp.utils

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

/**
 * Provides simple animation helpers for the UI. For this project we only
 * implement a fade‑in effect but additional animations could be added
 * here (e.g. slide in, rotate, scale) to enhance user experience as
 * required by the evaluation criteria.
 */
object AnimationsUtil {
    fun fadeIn(view: View, duration: Long = 300) {
        val animation: Animation = AlphaAnimation(0f, 1f)
        animation.duration = duration
        view.startAnimation(animation)
    }
}