package com.example.barbershopapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.barbershopapp.databinding.ActivityAddProductBinding;
import com.example.barbershopapp.utils.CameraUtil;
import com.example.barbershopapp.utils.Validator;
import com.example.barbershopapp.viewmodel.AddProductViewModel;
import java.io.File;

/**
 * Activity that allows the user to add a new product. It collects the
 * product's name, description, price and an optional photo. Input
 * validation is performed via [Validator].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0017"}, d2 = {"Lcom/example/barbershopapp/ui/AddProductActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/barbershopapp/databinding/ActivityAddProductBinding;", "cameraLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "currentPhotoFile", "Ljava/io/File;", "currentPhotoPath", "", "viewModel", "Lcom/example/barbershopapp/viewmodel/AddProductViewModel;", "getViewModel", "()Lcom/example/barbershopapp/viewmodel/AddProductViewModel;", "viewModel$delegate", "Lerror/NonExistentClass;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class AddProductActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.barbershopapp.databinding.ActivityAddProductBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.example.barbershopapp.viewmodel.AddProductViewModel viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentPhotoPath;
    private java.io.File currentPhotoFile;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> cameraLauncher = null;
    
    public AddProductActivity() {
        super();
    }
    
    private final com.example.barbershopapp.viewmodel.AddProductViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}