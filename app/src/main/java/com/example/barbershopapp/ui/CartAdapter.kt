package com.example.barbershopapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershopapp.R
import com.example.barbershopapp.model.CartItem
import com.google.android.material.button.MaterialButton
import java.util.Locale

/**
 * Adapter para mostrar items del carrito
 * VERSIÓN FULLSTACK PROFESIONAL CORREGIDA
 */
class CartAdapter(
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onRemoveItem: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems: List<CartItem> = emptyList()

    fun updateCartItems(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart, parent, false
        )
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount(): Int = cartItems.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textProductName: TextView = itemView.findViewById(R.id.textCartProductName)
        private val textProductPrice: TextView = itemView.findViewById(R.id.textCartProductPrice)
        private val textQuantity: TextView = itemView.findViewById(R.id.textCartQuantity)
        private val textSubtotal: TextView = itemView.findViewById(R.id.textCartSubtotal)
        private val buttonDecrease: MaterialButton = itemView.findViewById(R.id.buttonDecrease)
        private val buttonIncrease: MaterialButton = itemView.findViewById(R.id.buttonIncrease)
        private val buttonRemove: MaterialButton = itemView.findViewById(R.id.buttonRemoveItem)

        fun bind(cartItem: CartItem) {
            // Usar las propiedades correctas de CartItem
            val productName = cartItem.productName
            val productPrice = cartItem.productPrice
            val quantity = cartItem.quantity
            val subtotal = cartItem.totalPrice // Usar la propiedad calculada

            // Configurar textos
            textProductName.text = productName
            textProductPrice.text = String.format(Locale.US, "$%.2f", productPrice)
            textQuantity.text = quantity.toString()
            textSubtotal.text = String.format(Locale.US, "$%.2f", subtotal)

            // Botón disminuir cantidad
            buttonDecrease.setOnClickListener {
                if (quantity > 1) {
                    onQuantityChanged(cartItem, quantity - 1)
                }
            }
            buttonDecrease.isEnabled = quantity > 1

            // Botón aumentar cantidad (sin límite de stock por ahora)
            buttonIncrease.setOnClickListener {
                onQuantityChanged(cartItem, quantity + 1)
            }
            buttonIncrease.isEnabled = true // Siempre habilitado

            // Botón eliminar
            buttonRemove.setOnClickListener {
                onRemoveItem(cartItem)
            }
        }
    }
}
