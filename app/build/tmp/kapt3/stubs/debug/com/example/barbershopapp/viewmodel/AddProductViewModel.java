package com.example.barbershopapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.barbershopapp.data.AppDatabase;
import com.example.barbershopapp.data.Product;
import com.example.barbershopapp.data.ProductRepository;

/**
 * ViewModel for the AddProductActivity. Handles inserting new
 * products into the database. Using a ViewModel allows the insertion
 * to survive configuration changes and keeps the Activity free of
 * business logic.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/barbershopapp/viewmodel/AddProductViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "repository", "Lcom/example/barbershopapp/data/ProductRepository;", "addProduct", "Lkotlinx/coroutines/Job;", "name", "", "description", "price", "", "imageUri", "app_debug"})
public final class AddProductViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.barbershopapp.data.ProductRepository repository = null;
    
    public AddProductViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job addProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, double price, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUri) {
        return null;
    }
}