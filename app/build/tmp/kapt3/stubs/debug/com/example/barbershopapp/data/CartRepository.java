package com.example.barbershopapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * In‑memory repository for shopping cart items. This demonstrates state
 * management separate from the UI. In a production app you could
 * persist the cart using Room or another persistence mechanism.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0012"}, d2 = {"Lcom/example/barbershopapp/data/CartRepository;", "", "()V", "_cartItems", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/barbershopapp/data/CartItem;", "cartItems", "Landroidx/lifecycle/LiveData;", "getCartItems", "()Landroidx/lifecycle/LiveData;", "addItem", "", "product", "Lcom/example/barbershopapp/data/Product;", "getTotal", "", "removeItem", "app_debug"})
public final class CartRepository {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.barbershopapp.data.CartItem>> _cartItems = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.barbershopapp.data.CartItem>> cartItems = null;
    
    public CartRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.barbershopapp.data.CartItem>> getCartItems() {
        return null;
    }
    
    /**
     * Adds a product to the cart or increases quantity if already present.
     */
    public final void addItem(@org.jetbrains.annotations.NotNull()
    com.example.barbershopapp.data.Product product) {
    }
    
    /**
     * Removes a product from the cart or decreases quantity.
     */
    public final void removeItem(@org.jetbrains.annotations.NotNull()
    com.example.barbershopapp.data.Product product) {
    }
    
    /**
     * Calculates the total cost of items in the cart.
     */
    public final double getTotal() {
        return 0.0;
    }
}