package com.example.barbershopapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource

/**
 * Utility class for obtaining the user's current location using the fused
 * location provider. This demonstrates how to access native device
 * resources (location) safely. Permissions must be granted before
 * calling [getLastLocation].
 */
class LocationUtil(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    /**
     * Requests the current device location. The result is delivered via
     * [onResult]. If the location cannot be retrieved, null is passed.
     */
    @SuppressLint("MissingPermission")
    fun getLastLocation(onResult: (Location?) -> Unit) {
        fusedLocationClient.getCurrentLocation(
            com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location: Location? ->
            onResult(location)
        }.addOnFailureListener {
            onResult(null)
        }
    }
}