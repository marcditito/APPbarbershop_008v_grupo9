package com.example.barbershopapp.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

/**
 * Utilidades para funciones nativas del dispositivo
 * - Vibración del dispositivo
 * - Acceso a sensores (acelerómetro, proximidad, etc.)
 * Se integra sin afectar el código existente.
 */
object NativeDeviceUtils {

    private val TAG = "NativeDeviceUtils"

    /**
     * Vibra el dispositivo con duración especificada
     * @param context Contexto de la aplicación
     * @param duration Duración en milisegundos
     */
    fun vibrateDevice(context: Context, duration: Long = 100) {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(duration)
            }
            Log.d(TAG, "✅ Vibración ejecutada: ${duration}ms")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al vibrar: ${e.message}")
        }
    }

    /**
     * Vibra con patrón personalizado (múltiples pulsos)
     * @param context Contexto de la aplicación
     * @param pattern Array con patrones: [retraso, duración, retraso, duración, ...]
     */
    fun vibratePattern(context: Context, pattern: LongArray) {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Para Android 8.0+, usar createWaveform
                vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                // Para versiones anteriores, usar primer elemento del patrón como duración
                vibrateDevice(context, pattern.getOrNull(1) ?: 100)
            }
            Log.d(TAG, "✅ Patrón de vibración ejecutado")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al vibrar patrón: ${e.message}")
        }
    }

    /**
     * Vibración de éxito (patrón corto)
     * @param context Contexto de la aplicación
     */
    fun vibrationSuccess(context: Context) {
        vibratePattern(context, longArrayOf(0, 100, 50, 100))
    }

    /**
     * Vibración de error (patrón de alerta)
     * @param context Contexto de la aplicación
     */
    fun vibrationError(context: Context) {
        vibratePattern(context, longArrayOf(0, 150, 100, 150, 100, 150))
    }

    /**
     * Vibración de click (pequeña vibración)
     * @param context Contexto de la aplicación
     */
    fun vibrationClick(context: Context) {
        vibrateDevice(context, 50)
    }

    /**
     * Obtiene el acelerómetro del dispositivo
     * @param context Contexto de la aplicación
     * @return Sensor del acelerómetro o null si no está disponible
     */
    fun getAccelerometer(context: Context): Sensor? {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    /**
     * Obtiene el sensor de proximidad del dispositivo
     * @param context Contexto de la aplicación
     * @return Sensor de proximidad o null si no está disponible
     */
    fun getProximitySensor(context: Context): Sensor? {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }

    /**
     * Obtiene el sensor de luz del dispositivo
     * @param context Contexto de la aplicación
     * @return Sensor de luz o null si no está disponible
     */
    fun getLightSensor(context: Context): Sensor? {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    /**
     * Registra un listener para el acelerómetro
     * @param context Contexto de la aplicación
     * @param listener Implementación de SensorEventListener
     */
    fun registerAccelerometerListener(context: Context, listener: SensorEventListener) {
        try {
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val accelerometer = getAccelerometer(context)
            if (accelerometer != null) {
                sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
                Log.d(TAG, "✅ Listener de acelerómetro registrado")
            }
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al registrar acelerómetro: ${e.message}")
        }
    }

    /**
     * Desregistra un listener de sensor
     * @param context Contexto de la aplicación
     * @param listener Listener a desregistrar
     */
    fun unregisterSensorListener(context: Context, listener: SensorEventListener) {
        try {
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensorManager.unregisterListener(listener)
            Log.d(TAG, "✅ Listener de sensor desregistrado")
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error al desregistrar sensor: ${e.message}")
        }
    }

    /**
     * Detecta si el dispositivo se está moviendo basado en acelerómetro
     * @param sensorEvent Evento del sensor
     * @param threshold Umbral de movimiento
     * @return True si detecta movimiento
     */
    fun isDeviceMoving(sensorEvent: SensorEvent, threshold: Float = 2f): Boolean {
        val x = sensorEvent.values[0]
        val y = sensorEvent.values[1]
        val z = sensorEvent.values[2]

        val acceleration = kotlin.math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        return acceleration > threshold
    }

    /**
     * Listener de acelerómetro simplificado para detección de shakes
     */
    class ShakeDetectionListener(
        private val onShakeDetected: () -> Unit,
        private val threshold: Float = 15f
    ) : SensorEventListener {

        private var lastX = 0f
        private var lastY = 0f
        private var lastZ = 0f

        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = (x - lastX) + (y - lastY) + (z - lastZ)

            if (kotlin.math.abs(acceleration) > threshold) {
                Log.d(TAG, "🔀 Shake detectado!")
                onShakeDetected()
            }

            lastX = x
            lastY = y
            lastZ = z
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No necesaria para esta implementación
        }
    }
}

