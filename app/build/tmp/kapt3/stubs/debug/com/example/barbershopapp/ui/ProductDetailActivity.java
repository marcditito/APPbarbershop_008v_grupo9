package com.example.barbershopapp.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.barbershopapp.data.Product;
import com.example.barbershopapp.databinding.ActivityProductDetailBinding;
import com.example.barbershopapp.viewmodel.CartViewModel;
import com.example.barbershopapp.viewmodel.ProductViewModel;

/**
 * Displays the details of a single product. The product ID is passed via
 * the Intent extras. Users can add the product to the cart from here.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0017"}, d2 = {"Lcom/example/barbershopapp/ui/ProductDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/barbershopapp/databinding/ActivityProductDetailBinding;", "cartViewModel", "Lcom/example/barbershopapp/viewmodel/CartViewModel;", "getCartViewModel", "()Lcom/example/barbershopapp/viewmodel/CartViewModel;", "cartViewModel$delegate", "Lerror/NonExistentClass;", "productViewModel", "Lcom/example/barbershopapp/viewmodel/ProductViewModel;", "getProductViewModel", "()Lcom/example/barbershopapp/viewmodel/ProductViewModel;", "productViewModel$delegate", "displayProduct", "", "product", "Lcom/example/barbershopapp/data/Product;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class ProductDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.barbershopapp.databinding.ActivityProductDetailBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.example.barbershopapp.viewmodel.ProductViewModel productViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.barbershopapp.viewmodel.CartViewModel cartViewModel$delegate = null;
    
    public ProductDetailActivity() {
        super();
    }
    
    private final com.example.barbershopapp.viewmodel.ProductViewModel getProductViewModel() {
        return null;
    }
    
    private final com.example.barbershopapp.viewmodel.CartViewModel getCartViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void displayProduct(com.example.barbershopapp.data.Product product) {
    }
}