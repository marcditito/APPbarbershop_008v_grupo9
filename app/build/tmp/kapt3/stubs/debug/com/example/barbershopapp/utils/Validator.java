package com.example.barbershopapp.utils;

/**
 * Utility object containing reusable validation logic. Validations are
 * implemented here rather than in activities or fragments to keep UI
 * components simple and allow easy unit testing. Returns a map of
 * field names to error messages; if the map is empty all inputs are
 * valid.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005\u00a8\u0006\t"}, d2 = {"Lcom/example/barbershopapp/utils/Validator;", "", "()V", "validateProduct", "", "", "name", "description", "price", "app_debug"})
public final class Validator {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.barbershopapp.utils.Validator INSTANCE = null;
    
    private Validator() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> validateProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String price) {
        return null;
    }
}