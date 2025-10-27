package com.example.barbershopapp.utils

/**
 * Utility object containing reusable validation logic. Validations are
 * implemented here rather than in activities or fragments to keep UI
 * components simple and allow easy unit testing. Returns a map of
 * field names to error messages; if the map is empty all inputs are
 * valid.
 */
object Validator {
    fun validateProduct(name: String, description: String, price: String): Map<String, String> {
        val errors = mutableMapOf<String, String>()
        if (name.isBlank()) errors["name"] = "El nombre es obligatorio"
        if (description.isBlank()) errors["description"] = "La descripción es obligatoria"
        val priceValue = price.toDoubleOrNull()
        if (priceValue == null || priceValue <= 0.0) {
            errors["price"] = "El precio debe ser un número válido mayor a 0"
        }
        return errors
    }
}