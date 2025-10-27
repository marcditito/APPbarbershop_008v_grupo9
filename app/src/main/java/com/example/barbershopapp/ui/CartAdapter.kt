package com.example.barbershopapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershopapp.R
import com.example.barbershopapp.data.CartItem
import com.example.barbershopapp.utils.AnimationsUtil

/**
 * Adapter for displaying cart items in a RecyclerView. Simply binds
 * each cart item to the row layout. Images are placeholders, but you
 * could load the actual product image using the URI with a library
 * like Glide.
 */
class CartAdapter(private var cartItems: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.cartItemImage)
        private val name: TextView = itemView.findViewById(R.id.cartItemName)
        private val quantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        private val price: TextView = itemView.findViewById(R.id.cartItemPrice)

        fun bind(item: CartItem) {
            name.text = item.product.name
            quantity.text = "Cantidad: ${'$'}{item.quantity}"
            price.text = "Precio: ${'$'}${String.format("%.2f", item.product.price * item.quantity)}"
            // For brevity we do not load actual images here.
            AnimationsUtil.fadeIn(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateData(newList: List<CartItem>) {
        cartItems = newList
        notifyDataSetChanged()
    }
}