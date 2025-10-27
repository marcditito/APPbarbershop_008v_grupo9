package com.example.barbershopapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Helper class for dealing with camera intents. Creates temporary files and
 * dispatches an intent to capture a photo. The captured image is saved
 * directly to the given file rather than returned via onActivityResult.
 */
object CameraUtil {
    const val REQUEST_IMAGE_CAPTURE = 1

    /**
     * Creates a temporary image file in the app's external files directory.
     */
    fun createImageFile(activity: Activity): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = activity.getExternalFilesDir(null)
        return File.createTempFile(
            "JPEG_${'$'}{timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    /**
     * Returns an intent to launch the camera app. The captured photo will be
     * written to the provided file via a FileProvider. The caller must
     * register an ActivityResultContract to handle the result.
     */
    fun dispatchTakePictureIntent(activity: Activity, photoFile: File): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoURI: Uri = FileProvider.getUriForFile(
            activity,
            activity.packageName + ".provider",
            photoFile
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        return intent
    }
}