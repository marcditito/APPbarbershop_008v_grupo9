package com.example.barbershopapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.databinding.ActivityAddProductBinding
import com.example.barbershopapp.utils.CameraUtil
import com.example.barbershopapp.utils.Validator
import com.example.barbershopapp.viewmodel.AddProductViewModel
import java.io.File

/**
 * Activity that allows the user to add a new product. It collects the
 * product's name, description, price and an optional photo. Input
 * validation is performed via [Validator].
 */
class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private val viewModel: AddProductViewModel by viewModels()
    private var currentPhotoPath: String? = null
    private lateinit var currentPhotoFile: File

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            currentPhotoPath?.let {
                binding.imagePreview.setImageURI(android.net.Uri.parse(it))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTakePicture.setOnClickListener {
            // Create a file for the photo and launch the camera intent
            currentPhotoFile = CameraUtil.createImageFile(this)
            currentPhotoPath = currentPhotoFile.absolutePath
            val intent = CameraUtil.dispatchTakePictureIntent(this, currentPhotoFile)
            cameraLauncher.launch(intent)
        }

        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val desc = binding.editDescription.text.toString()
            val price = binding.editPrice.text.toString()
            val errors = Validator.validateProduct(name, desc, price)
            if (errors.isEmpty()) {
                viewModel.addProduct(name, desc, price.toDouble(), currentPhotoPath)
                Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                if (errors.containsKey("name")) binding.editName.error = errors["name"]
                if (errors.containsKey("description")) binding.editDescription.error = errors["description"]
                if (errors.containsKey("price")) binding.editPrice.error = errors["price"]
            }
        }
    }
}