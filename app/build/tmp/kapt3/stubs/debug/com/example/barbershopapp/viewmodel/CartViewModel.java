package com.example.barbershopapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.barbershopapp.data.Product;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lcom/example/barbershopapp/viewmodel/CartViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_cartItems", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/barbershopapp/data/Product;", "cartItems", "Landroidx/lifecycle/LiveData;", "getCartItems", "()Landroidx/lifecycle/LiveData;", "addToCart", "", "product", "clearCart", "removeFromCart", "app_debug"})
public final class CartViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.barbershopapp.data.Product>> _cartItems = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.barbershopapp.data.Product>> cartItems = null;
    
    public CartViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.barbershopapp.data.Product>> getCartItems() {
        return null;
    }
    
    public final void addToCart(@org.jetbrains.annotations.NotNull()
    com.example.barbershopapp.data.Product product) {
    }
    
    public final void removeFromCart(@org.jetbrains.annotations.NotNull()
    com.example.barbershopapp.data.Product product) {
    }
    
    public final void clearCart() {
    }
}