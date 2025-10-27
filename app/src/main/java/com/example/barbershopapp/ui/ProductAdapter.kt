package com.example.barbershopapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershopapp.R
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.utils.AnimationsUtil

/**
 * RecyclerView adapter used to display a list of [Product] items. Handles
 * click callbacks for opening the product detail and adding items to
 * the cart. Uses a simple fade‑in animation when binding views.
 */
class ProductAdapter(
    private var products: List<Product>,
    private val onItemClick: (Product) -> Unit,
    private val onAddToCart: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val name: TextView = itemView.findViewById(R.id.textName)
        private val description: TextView = itemView.findViewById(R.id.textDescription)
        private val price: TextView = itemView.findViewById(R.id.textPrice)
        private val buttonAdd: Button = itemView.findViewById(R.id.buttonAdd)

        fun bind(product: Product) {
            name.text = product.name
            description.text = product.description
            price.text = String.format("$%.2f", product.price)
            // Loading image from URI could be implemented with Glide or Coil. For simplicity we use a placeholder.
            buttonAdd.setOnClickListener {
                onAddToCart(product)
            }
            itemView.setOnClickListener {
                onItemClick(product)
            }
            AnimationsUtil.fadeIn(itemView)
        }
    }

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun updateData(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }
}