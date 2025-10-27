package com.example.barbershopapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.barbershopapp.data.AppDatabase;
import com.example.barbershopapp.data.Product;
import com.example.barbershopapp.data.ProductRepository;

/**
 * ViewModel responsible for exposing product data to the UI. It
 * abstracts the underlying repository and provides an insert method to
 * add default products. Observing [allProducts] allows the UI to react
 * to database updates automatically.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/barbershopapp/viewmodel/ProductViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "allProducts", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/barbershopapp/data/Product;", "getAllProducts", "()Landroidx/lifecycle/LiveData;", "repository", "Lcom/example/barbershopapp/data/ProductRepository;", "insert", "Lkotlinx/coroutines/Job;", "product", "app_debug"})
public final class ProductViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.barbershopapp.data.ProductRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.barbershopapp.data.Product>> allProducts = null;
    
    public ProductViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.barbershopapp.data.Product>> getAllProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    com.example.barbershopapp.data.Product product) {
        return null;
    }
}