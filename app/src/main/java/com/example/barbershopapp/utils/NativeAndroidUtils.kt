package com.example.barbershopapp.utils

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import kotlin.math.sqrt

/**
 * Utilidades para funciones nativas de Android
 * - Vibración del dispositivo
 * - Detección de sensores (acelerómetro, proximidad, luz)
 * - Eventos de movimiento
 */
object NativeAndroidUtils {

    private const val TAG = "NativeAndroidUtils"

    /**
     * Hacer vibrar el dispositivo (haptic feedback)
     * @param context Contexto de la aplicación
     * @param duration Duración en milisegundos
     * @param intensity Intensidad (0-255)
     */
    fun vibrateDevice(context: Context, duration: Long = 200, intensity: Int = 150) {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            vibrator?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    it.vibrate(VibrationEffect.createOneShot(duration, intensity))
                } else {
                    @Suppress("DEPRECATION")
                    it.vibrate(duration)
                }
                Log.d(TAG, "✓ Vibración activada: ${duration}ms, intensidad: $intensity")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error activando vibración", e)
        }
    }

    /**
     * Patrón de vibración personalizado (ej: éxito, error, etc)
     * @param context Contexto de la aplicación
     * @param pattern Array con tiempos [pausa_inicial, vibración, pausa, vibración, ...]
     */
    fun vibratePattern(context: Context, pattern: LongArray) {
        try {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            vibrator?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val amplitudes = IntArray(pattern.size) { 150 }
                    it.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, -1))
                } else {
                    @Suppress("DEPRECATION")
                    it.vibrate(pattern, -1)
                }
                Log.d(TAG, "✓ Patrón de vibración activado")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error con patrón de vibración", e)
        }
    }

    /**
     * Patrón éxito: vibración rápida y corta
     */
    fun vibrateSuccess(context: Context) {
        vibratePattern(context, longArrayOf(0, 100, 50, 100))
    }

    /**
     * Patrón error: vibración larga
     */
    fun vibrateError(context: Context) {
        vibrateDevice(context, 300, 200)
    }

    /**
     * Patrón click: vibración muy corta
     */
    fun vibrateClick(context: Context) {
        vibrateDevice(context, 50, 100)
    }

    /**
     * Listener para eventos del acelerómetro
     * Detecta sacudidas y movimientos
     */
    class AccelerometerListener(
        private val onShakeDetected: () -> Unit,
        private val shakeThreshold: Float = 30f
    ) : SensorEventListener {

        private var lastShakeTime = 0L
        private val shakeDetectionDelay = 500L // ms

        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                val x = it.values[0]
                val y = it.values[1]
                val z = it.values[2]

                // Calcular magnitud de aceleración
                val acceleration = sqrt(x * x + y * y + z * z) - 9.81f

                // Detectar sacudida
                if (acceleration > shakeThreshold) {
                    val currentTime = System.currentTimeMillis()
                    if (currentTime - lastShakeTime > shakeDetectionDelay) {
                        lastShakeTime = currentTime
                        Log.d(TAG, "✓ Sacudida detectada: $acceleration m/s²")
                        onShakeDetected()
                    }
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No requerido
        }
    }

    /**
     * Listener para sensor de proximidad
     */
    class ProximityListener(
        private val onProximityChange: (isNear: Boolean) -> Unit
    ) : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent?) {
            event?.let {
                val distance = it.values[0]
                val isNear = distance < 5f // Menos de 5cm es "cerca"
                Log.d(TAG, "📍 Proximidad: ${distance}cm, Cercano: $isNear")
                onProximityChange(isNear)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // No requerido
        }
    }

    /**
     * Gestionar sensores del dispositivo
     */
    class SensorManagerHelper(context: Context) {

        private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? android.hardware.SensorManager
        private var accelerometerListener: AccelerometerListener? = null
        private var proximityListener: ProximityListener? = null

        /**
         * Iniciar detección de acelerómetro
         */
        fun startAccelerometerDetection(onShakeDetected: () -> Unit) {
            try {
                val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                accelerometer?.let {
                    accelerometerListener = AccelerometerListener(onShakeDetected)
                    sensorManager.registerListener(
                        accelerometerListener,
                        it,
                        android.hardware.SensorManager.SENSOR_DELAY_NORMAL
                    )
                    Log.d(TAG, "✓ Acelerómetro iniciado")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error iniciando acelerómetro", e)
            }
        }

        /**
         * Detener detección de acelerómetro
         */
        fun stopAccelerometerDetection() {
            try {
                accelerometerListener?.let {
                    sensorManager?.unregisterListener(it)
                    Log.d(TAG, "✓ Acelerómetro detenido")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error deteniendo acelerómetro", e)
            }
        }

        /**
         * Iniciar detección de proximidad
         */
        fun startProximityDetection(onProximityChange: (isNear: Boolean) -> Unit) {
            try {
                val proximitySensor = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                proximitySensor?.let {
                    proximityListener = ProximityListener(onProximityChange)
                    sensorManager.registerListener(
                        proximityListener,
                        it,
                        android.hardware.SensorManager.SENSOR_DELAY_NORMAL
                    )
                    Log.d(TAG, "✓ Sensor de proximidad iniciado")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error iniciando sensor de proximidad", e)
            }
        }

        /**
         * Detener detección de proximidad
         */
        fun stopProximityDetection() {
            try {
                proximityListener?.let {
                    sensorManager?.unregisterListener(it)
                    Log.d(TAG, "✓ Sensor de proximidad detenido")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error deteniendo sensor de proximidad", e)
            }
        }
    }
}

