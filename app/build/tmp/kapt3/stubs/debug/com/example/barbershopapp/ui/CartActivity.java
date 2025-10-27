package com.example.barbershopapp.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.barbershopapp.R;
import com.example.barbershopapp.data.Product;
import com.example.barbershopapp.databinding.ActivityCartBinding;
import com.example.barbershopapp.viewmodel.CartViewModel;
import java.text.NumberFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\u0016\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/example/barbershopapp/ui/CartActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/barbershopapp/ui/CartAdapter;", "binding", "Lcom/example/barbershopapp/databinding/ActivityCartBinding;", "viewModel", "Lcom/example/barbershopapp/viewmodel/CartViewModel;", "getViewModel", "()Lcom/example/barbershopapp/viewmodel/CartViewModel;", "viewModel$delegate", "Lerror/NonExistentClass;", "observeCart", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupRecyclerView", "setupToolbar", "updateTotal", "products", "", "Lcom/example/barbershopapp/data/Product;", "app_debug"})
public final class CartActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.barbershopapp.databinding.ActivityCartBinding binding;
    private com.example.barbershopapp.ui.CartAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private final com.example.barbershopapp.viewmodel.CartViewModel viewModel$delegate = null;
    
    public CartActivity() {
        super();
    }
    
    private final com.example.barbershopapp.viewmodel.CartViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void observeCart() {
    }
    
    private final void updateTotal(java.util.List<com.example.barbershopapp.data.Product> products) {
    }
}