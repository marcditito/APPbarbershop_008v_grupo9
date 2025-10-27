package com.example.barbershopapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.barbershopapp.R;
import com.example.barbershopapp.data.Product;
import com.example.barbershopapp.databinding.ActivityMainBinding;
import com.example.barbershopapp.viewmodel.CartViewModel;
import com.example.barbershopapp.viewmodel.ProductViewModel;

/**
 * The entry point into the application. Displays a list of products and
 * allows the user to navigate to the cart or to product details. It
 * also inserts a few sample products on first run so that the list
 * isn't empty when the app starts.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\fR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0013\u0010\nR\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/barbershopapp/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/barbershopapp/ui/ProductAdapter;", "binding", "Lcom/example/barbershopapp/databinding/ActivityMainBinding;", "cartViewModel", "error/NonExistentClass", "getCartViewModel", "()Lerror/NonExistentClass;", "cartViewModel$delegate", "Lerror/NonExistentClass;", "permissions", "pickMedia", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/PickVisualMediaRequest;", "kotlin.jvm.PlatformType", "productViewModel", "getProductViewModel", "productViewModel$delegate", "requestPermissionLauncher", "", "", "checkAndRequestPermissions", "", "handleSelectedImage", "uri", "Landroid/net/Uri;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupUI", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.barbershopapp.databinding.ActivityMainBinding binding;
    private com.example.barbershopapp.ui.ProductAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Object productViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Object cartViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<androidx.activity.result.PickVisualMediaRequest> pickMedia = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Object permissions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestPermissionLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    private final java.lang.Object getProductViewModel() {
        return null;
    }
    
    private final java.lang.Object getCartViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void checkAndRequestPermissions() {
    }
    
    private final void setupUI() {
    }
    
    private final void handleSelectedImage(android.net.Uri uri) {
    }
}