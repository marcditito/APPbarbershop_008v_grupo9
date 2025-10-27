package com.example.barbershopapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershopapp.R
import com.example.barbershopapp.data.Product
import java.text.NumberFormat
import java.util.*

class CartAdapter(
    private var products: List<Product> = emptyList(),
    private val onRemoveItem: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val name: TextView = itemView.findViewById(R.id.textName)
        private val price: TextView = itemView.findViewById(R.id.textPrice)
        private val buttonRemove: ImageButton = itemView.findViewById(R.id.buttonRemove)

        fun bind(product: Product) {
            name.text = product.name
            val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            price.text = numberFormat.format(product.price)
            buttonRemove.setOnClickListener {
                onRemoveItem(product)
            }
        }
    }

    fun updateCart(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}
