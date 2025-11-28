package com.example.barbershopapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Credenciales estáticas
    private val validEmail = "axxelduran342@gmail.com"
    private val validPassword = "axelduran8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("LoginActivity", "=== LoginActivity onCreate iniciado ===")

        try {
            // Ocultar ActionBar
            supportActionBar?.hide()

            binding = ActivityLoginBinding.inflate(layoutInflater)
            setContentView(binding.root)

            Log.d("LoginActivity", "✓ Layout configurado")

            setupUI()

            Log.d("LoginActivity", "✓ LoginActivity inicializada correctamente")

        } catch (e: Exception) {
            Log.e("LoginActivity", "❌ Error crítico en LoginActivity", e)
            showError("Error inicializando pantalla de login")
        }
    }

    private fun setupUI() {
        try {
            Log.d("LoginActivity", "Configurando UI...")

            // Pre-llenar con credenciales estáticas
            binding.emailInput.setText(validEmail)
            binding.passwordInput.setText(validPassword)

            // Botón de login
            binding.buttonLogin.setOnClickListener {
                performLogin()
            }

            // Ocultar elementos no necesarios
            try {
                binding.buttonBiometric?.visibility = android.view.View.GONE
                binding.dividerLayout?.visibility = android.view.View.GONE
                binding.textForgotPassword?.visibility = android.view.View.GONE
                binding.textSignUp?.visibility = android.view.View.GONE
            } catch (e: Exception) {
                Log.w("LoginActivity", "Algunos elementos opcionales no encontrados: ${e.message}")
            }

            // Agregar hints útiles
            binding.emailInputLayout.helperText = "Credenciales fijas para pruebas"
            binding.passwordInputLayout.helperText = "Usuario de demostración"

            Log.d("LoginActivity", "✓ UI configurada correctamente")

        } catch (e: Exception) {
            Log.e("LoginActivity", "❌ Error configurando UI", e)
            throw e
        }
    }

    private fun performLogin() {
        try {
            Log.d("LoginActivity", "Iniciando proceso de login...")

            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            Log.d("LoginActivity", "Email ingresado: $email")

            // Validar credenciales estáticas
            if (email == validEmail && password == validPassword) {
                Log.d("LoginActivity", "✓ Credenciales válidas")

                // Mostrar mensaje de éxito
                showMessage("Login exitoso")

                // Pequeño delay para mostrar el mensaje
                binding.root.postDelayed({
                    navigateToMainActivity()
                }, 500)

            } else {
                Log.w("LoginActivity", "❌ Credenciales inválidas")
                showError("Credenciales incorrectas. Use: $validEmail / $validPassword")
            }

        } catch (e: Exception) {
            Log.e("LoginActivity", "❌ Error en proceso de login", e)
            showError("Error durante el login: ${e.message}")
        }
    }

    private fun navigateToMainActivity() {
        try {
            Log.d("LoginActivity", "Navegando a MainActivity...")

            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            // Verificar que MainActivity existe
            val packageManager = packageManager
            if (intent.resolveActivity(packageManager) != null) {
                Log.d("LoginActivity", "✓ MainActivity encontrada, iniciando...")
                startActivity(intent)
                finish()
            } else {
                Log.e("LoginActivity", "❌ MainActivity no encontrada")
                showError("Error: Pantalla principal no disponible")
            }

        } catch (e: Exception) {
            Log.e("LoginActivity", "❌ Error al navegar a MainActivity", e)
            showError("Error al abrir la aplicación principal: ${e.message}")
        }
    }

    private fun showError(message: String) {
        Log.w("LoginActivity", "Mostrando error: $message")
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        binding.emailInputLayout.error = null
        binding.passwordInputLayout.error = message
    }

    private fun showMessage(message: String) {
        Log.d("LoginActivity", "Mensaje: $message")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

