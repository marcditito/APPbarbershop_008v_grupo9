package com.example.barbershopapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.barbershopapp.R

/**
 * NavHostActivity - Actividad base para navegación con Navigation Component
 * Proporciona navegación consistente entre fragmentos sin afectar Activities existentes
 */
class NavHostActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Nota: Esta actividad requiere un layout de navigation que será creado
        // Por ahora es una referencia para futura implementación
        Log.d("NavHostActivity", "NavHostActivity inicializada")
    }

    /**
     * Navega a un destino por ID de recurso
     * @param destinationId ID del destino en el grafo de navegación
     * @param bundle Argumentos opcionales para pasar entre fragmentos
     */
    fun navigateTo(destinationId: Int, bundle: Bundle? = null) {
        try {
            navController.navigate(destinationId, bundle)
            Log.d("NavHostActivity", "✅ Navegación a destino: $destinationId")
        } catch (e: Exception) {
            Log.e("NavHostActivity", "❌ Error en navegación: ${e.message}")
        }
    }

    /**
     * Navega hacia atrás en el stack de navegación
     */
    fun navigateBack() {
        try {
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
                Log.d("NavHostActivity", "✅ Navegación hacia atrás ejecutada")
            } else {
                finish()
            }
        } catch (e: Exception) {
            Log.e("NavHostActivity", "❌ Error al navegar hacia atrás: ${e.message}")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

