package com.example.barbershopapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Helper class for dealing with camera intents. Creates temporary files and
 * dispatches an intent to capture a photo. The captured image is saved
 * directly to the given file rather than returned via onActivityResult.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/barbershopapp/utils/CameraUtil;", "", "()V", "REQUEST_IMAGE_CAPTURE", "", "createImageFile", "Ljava/io/File;", "activity", "Landroid/app/Activity;", "dispatchTakePictureIntent", "Landroid/content/Intent;", "photoFile", "app_debug"})
public final class CameraUtil {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.barbershopapp.utils.CameraUtil INSTANCE = null;
    
    private CameraUtil() {
        super();
    }
    
    /**
     * Creates a temporary image file in the app's external files directory.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.io.File createImageFile(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    /**
     * Returns an intent to launch the camera app. The captured photo will be
     * written to the provided file via a FileProvider. The caller must
     * register an ActivityResultContract to handle the result.
     */
    @org.jetbrains.annotations.NotNull()
    public final android.content.Intent dispatchTakePictureIntent(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.io.File photoFile) {
        return null;
    }
}