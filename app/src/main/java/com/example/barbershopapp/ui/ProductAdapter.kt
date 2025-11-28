package com.example.barbershopapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershopapp.R
import com.example.barbershopapp.model.Product
import com.google.android.material.button.MaterialButton

/**
 * Adapter para mostrar productos en RecyclerView con layout personalizado
 */
class ProductAdapter(
    private val onProductClick: (Product) -> Unit,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products: List<Product> = emptyList()

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_product, parent, false
        )
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageProduct: ImageView = itemView.findViewById(R.id.imageProduct)
        private val textProductName: TextView = itemView.findViewById(R.id.textProductName)
        private val textProductDescription: TextView = itemView.findViewById(R.id.textProductDescription)
        private val textProductPrice: TextView = itemView.findViewById(R.id.textProductPrice)
        private val textProductStock: TextView = itemView.findViewById(R.id.textProductStock)
        private val buttonAddToCart: MaterialButton = itemView.findViewById(R.id.buttonAddToCart)

        fun bind(product: Product) {
            textProductName.text = product.name
            textProductDescription.text = product.description
            textProductPrice.text = "$${String.format("%.2f", product.price)}"
            textProductStock.text = "Stock: ${product.stock}"

            // Click en el producto para ver detalles
            itemView.setOnClickListener {
                onProductClick(product)
            }

            // Click en el botón para agregar al carrito
            buttonAddToCart.setOnClickListener {
                onAddToCartClick(product)
            }

            // Cambiar color del botón según el stock
            if (product.stock > 0) {
                buttonAddToCart.isEnabled = true
                buttonAddToCart.text = "Agregar al Carrito"
            } else {
                buttonAddToCart.isEnabled = false
                buttonAddToCart.text = "Sin Stock"
            }
        }
    }
}
