package com.example.barbershopapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershopapp.R
import com.example.barbershopapp.data.Product
import com.example.barbershopapp.databinding.ActivityMainBinding
import com.example.barbershopapp.viewmodel.CartViewModel
import com.example.barbershopapp.viewmodel.ProductViewModel

/**
 * The entry point into the application. Displays a list of products and
 * allows the user to navigate to the cart or to product details. It
 * also inserts a few sample products on first run so that the list
 * isn't empty when the app starts.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    private val productViewModel by lazy {
        ViewModelProvider(this)[ProductViewModel::class.java]
    }

    private val cartViewModel by lazy {
        ViewModelProvider(this)[CartViewModel::class.java]
    }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            handleSelectedImage(uri)
        }
    }

    private val permissions = mutableListOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ).apply {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            add(Manifest.permission.READ_MEDIA_IMAGES)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
            }
        }
    }.toTypedArray()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            setupUI()
        } else {
            Toast.makeText(
                this,
                "Se requieren todos los permisos para el funcionamiento correcto",
                Toast.LENGTH_LONG
            ).show()
            // Aún así intentamos configurar la UI
            setupUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkAndRequestPermissions()
    }

    private fun checkAndRequestPermissions() {
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isEmpty()) {
            setupUI()
        } else {
            requestPermissionLauncher.launch(permissionsToRequest)
        }
    }

    private fun setupUI() {
        // Configurar RecyclerView
        adapter = ProductAdapter(
            products = emptyList(),
            onItemClick = { product ->
                // Implementar navegación al detalle del producto
                startActivity(Intent(this, ProductDetailActivity::class.java).apply {
                    putExtra("product_id", product.id)
                })
            },
            onAddToCart = { product ->
                cartViewModel.addToCart(product)
                Toast.makeText(this, R.string.added_to_cart, Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = this@MainActivity.adapter
        }

        // Configurar FAB
        binding.fabAddProduct.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        binding.fabCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        // Observar cambios en los productos
        productViewModel.allProducts.observe(this) { products ->
            adapter.updateProducts(products)
        }
    }

    private fun handleSelectedImage(uri: Uri) {
        // Implementar manejo de imagen seleccionada
        Toast.makeText(this, "Imagen seleccionada: $uri", Toast.LENGTH_SHORT).show()
    }
}