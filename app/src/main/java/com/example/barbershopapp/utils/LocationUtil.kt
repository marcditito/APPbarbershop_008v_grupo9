package com.example.barbershopapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationUtil(private val context: Context) {
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    suspend fun getLastLocation(): Location? = suspendCancellableCoroutine { continuation ->
        try {
            // Verificar permisos antes de acceder a la ubicación
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Sin permisos, devolver null
                continuation.resume(null)
                return@suspendCancellableCoroutine
            }

            // Ahora sí podemos acceder a la ubicación de forma segura
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    continuation.resume(location)
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        } catch (e: SecurityException) {
            continuation.resume(null)
        } catch (e: Exception) {
            continuation.resume(null)
        }
    }

    fun hasLocationPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}
