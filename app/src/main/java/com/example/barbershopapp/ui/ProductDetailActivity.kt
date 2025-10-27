package com.example.barbershopapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.databinding.ActivityProductDetailBinding
import com.example.barbershopapp.viewmodel.CartViewModel
import com.example.barbershopapp.viewmodel.ProductViewModel

/**
 * Displays the details of a single product. The product ID is passed via
 * the Intent extras. Users can add the product to the cart from here.
 */
class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra("productId", -1)
        if (productId != -1) {
            productViewModel.allProducts.observe(this) { list ->
                val product = list.find { it.id == productId }
                product?.let { displayProduct(it) }
            }
        }

        binding.buttonAddToCart.setOnClickListener {
            val id = intent.getIntExtra("productId", -1)
            productViewModel.allProducts.value?.find { it.id == id }?.let { product ->
                cartViewModel.addToCart(product)
                finish()
            }
        }
    }

    private fun displayProduct(product: Product) {
        binding.detailName.text = product.name
        binding.detailDescription.text = product.description
        binding.detailPrice.text = "$${'$'}{product.price}"
        // You could load the image from product.imageUri using Glide here
    }
}