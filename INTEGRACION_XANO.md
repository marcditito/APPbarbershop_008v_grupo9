# 🔗 Integración BarberShop App con Xano

## 📋 Resumen

Se ha implementado una integración completa con **Xano** para la aplicación BarberShop, incluyendo autenticación, gestión de productos, carrito de compras y procesamiento de ventas.

## 🏗️ Arquitectura Implementada

### 📁 Estructura de Archivos

```
app/src/main/java/com/example/barbershopapp/
├── data/
│   ├── api/
│   │   ├── AuthApiService.kt          # API de autenticación
│   │   ├── SalesApiService.kt         # API de ventas/productos
│   │   └── NetworkModule.kt           # Configuración Retrofit/OkHttp
│   ├── model/
│   │   ├── AuthModels.kt              # Modelos de autenticación
│   │   └── SalesModels.kt             # Modelos de productos/ventas
│   └── repository/
│       ├── AuthRepository.kt          # Repositorio de autenticación
│       └── SalesRepository.kt         # Repositorio de ventas
├── viewmodel/
│   ├── LoginViewModel.kt              # ViewModel de login (actualizado)
│   ├── XanoProductViewModel.kt        # ViewModel de productos con Xano
│   └── XanoCartViewModel.kt           # ViewModel de carrito con Xano
└── ui/
    ├── TestXanoActivity.kt            # Actividad de pruebas
    └── [otros archivos existentes]
```

### 🔧 URLs de Xano Configuradas

- **Login API**: `https://x8ki-letl-twmt.n7.xano.io/api:PJ2xSnM6/`
- **Sales API**: `https://x8ki-letl-twmt.n7.xano.io/api:W-lgR84A/`

## 🚀 Funcionalidades Implementadas

### 🔐 Autenticación
- ✅ Login con email/contraseña
- ✅ Registro de nuevos usuarios
- ✅ Logout y gestión de tokens
- ✅ Renovación automática de tokens
- ✅ Modo demo para desarrollo

### 📦 Productos
- ✅ Obtener lista de productos
- ✅ Crear nuevos productos
- ✅ Actualizar productos existentes
- ✅ Eliminar productos
- ✅ Sincronización con base de datos local (Room)

### 🛒 Carrito de Compras
- ✅ Agregar productos al carrito
- ✅ Actualizar cantidades
- ✅ Eliminar productos del carrito
- ✅ Vaciar carrito completo
- ✅ Calcular totales automáticamente

### 💰 Ventas
- ✅ Procesar compras (checkout)
- ✅ Historial de ventas
- ✅ Detalles de ventas individuales

## 🧪 Cómo Probar la Integración

### 1. 🏃‍♂️ Ejecución Rápida

1. **Compilar la app**:
   ```bash
   ./gradlew assembleDebug
   ```

2. **Ejecutar en dispositivo/emulador**

3. **Acceso a pruebas**: 
   - En MainActivity, hacer **long press** en el toolbar superior
   - Se abrirá TestXanoActivity

### 2. 🔍 Actividad de Pruebas (TestXanoActivity)

La actividad `TestXanoActivity` incluye botones para probar todas las funcionalidades:

#### 🔐 Autenticación
- **Login Xano**: Prueba login real con Xano
- **Login Demo**: Login local sin backend
- **Logout**: Cerrar sesión

#### 📦 Productos
- **Cargar**: Obtener lista de productos de Xano
- **Crear**: Crear producto de prueba
- **Obtener Producto**: Obtener producto específico

#### 🛒 Carrito
- **Cargar**: Obtener carrito actual
- **Agregar**: Agregar producto al carrito
- **Procesar Compra**: Realizar checkout

### 3. 📱 Flujo de Usuario Normal

1. **Login**:
   ```kotlin
   // Credenciales demo
   Email: demo@barbershop.com
   Password: 123456
   ```

2. **Navegar por productos**: MainActivity mostrará productos de Xano

3. **Agregar al carrito**: Botón "+" en cada producto

4. **Ver carrito**: Icono del carrito en toolbar

5. **Procesar compra**: Botón "Comprar" en el carrito

## 📊 ViewModels y Estados

### 🔐 LoginViewModel

```kotlin
// Observar estados de login
loginViewModel.loginState.observe(this) { state ->
    when (state) {
        is LoginState.Success -> { /* Login exitoso */ }
        is LoginState.Error -> { /* Manejar error */ }
        is LoginState.Loading -> { /* Mostrar loading */ }
        // ... más estados
    }
}

// Hacer login
loginViewModel.login(email, password)
```

### 📦 XanoProductViewModel

```kotlin
// Cargar productos
productViewModel.loadXanoProducts()

// Crear producto
productViewModel.createXanoProduct(
    name = "Producto",
    description = "Descripción",
    price = 25.99,
    imageUrl = null,
    category = "Categoría",
    stock = 10
)

// Observar estados
productViewModel.productState.observe(this) { state ->
    when (state) {
        is ProductState.XanoLoadSuccess -> {
            // productos cargados: state.products
        }
        is ProductState.XanoCreateSuccess -> {
            // producto creado: state.product
        }
        // ... más estados
    }
}
```

### 🛒 XanoCartViewModel

```kotlin
// Agregar al carrito
cartViewModel.addToCart(productId, quantity)

// Procesar compra
cartViewModel.processPurchase("Cliente", "email@cliente.com")

// Observar estados
cartViewModel.cartState.observe(this) { state ->
    when (state) {
        is CartState.LoadSuccess -> {
            // carrito: state.cart
        }
        is CartState.PurchaseSuccess -> {
            // venta: state.sale
        }
        // ... más estados
    }
}
```

## 🔧 Configuración de Xano

### 📝 Endpoints Esperados

#### Autenticación (`/api:PJ2xSnM6/`)
- `POST /auth/login`
- `POST /auth/signup`
- `POST /auth/logout`
- `GET /auth/me`
- `POST /auth/refresh`

#### Ventas (`/api:W-lgR84A/`)
- `GET /products`
- `POST /products`
- `PUT /products/{id}`
- `DELETE /products/{id}`
- `GET /cart`
- `POST /cart/add`
- `PUT /cart/item/{itemId}`
- `DELETE /cart/item/{itemId}`
- `POST /sales`
- `GET /sales`

### 📋 Estructura de Datos Esperada

#### Usuario
```json
{
  "id": 1,
  "name": "Usuario",
  "email": "usuario@email.com",
  "created_at": "2024-01-01T00:00:00Z"
}
```

#### Producto
```json
{
  "id": 1,
  "name": "Producto",
  "description": "Descripción del producto",
  "price": 25.99,
  "imageUrl": "https://...",
  "category": "Categoría",
  "stock": 10,
  "isAvailable": true,
  "created_at": "2024-01-01T00:00:00Z"
}
```

## 🐛 Debugging y Logs

### 📊 Ver Logs
```bash
adb logcat -s "NetworkModule" "AuthRepository" "SalesRepository" "TestXanoActivity"
```

### 🔍 Tags de Log Útiles
- `NetworkModule`: Requests HTTP
- `AuthRepository`: Operaciones de autenticación
- `SalesRepository`: Operaciones de productos/carrito
- `LoginViewModel`: Estados de login
- `XanoProductViewModel`: Estados de productos
- `XanoCartViewModel`: Estados de carrito

## 🔄 Sincronización Room ↔ Xano

### 📥 De Xano a Room
```kotlin
// Guardar productos de Xano localmente
productViewModel.saveXanoProductsLocally()
```

### 📤 De Room a Xano
```kotlin
// Subir productos locales a Xano
productViewModel.syncLocalToXano()
```

## 🎯 Próximos Pasos

1. **Configurar endpoints reales** en tu Xano
2. **Ajustar modelos de datos** según tu estructura
3. **Implementar manejo de imágenes** (upload)
4. **Agregar paginación** para listas grandes
5. **Implementar caché** para mejor performance
6. **Agregar notificaciones push** (opcional)

## 🆘 Solución de Problemas

### ❌ Error de Conexión
- Verificar URLs de Xano
- Comprobar conectividad de internet
- Revisar logs de NetworkModule

### 🔐 Error de Autenticación
- Verificar credenciales
- Comprobar estructura de respuesta de login
- Revisar manejo de tokens

### 📦 Error de Productos
- Verificar endpoint de productos
- Comprobar autenticación requerida
- Revisar estructura de datos

## 📞 Contacto y Soporte

Si encuentras problemas:
1. Revisar logs en TestXanoActivity
2. Verificar configuración de Xano
3. Comprobar estructura de datos
4. Validar URLs y endpoints

---

**🎉 ¡La integración está lista! Ahora puedes conectar tu app con Xano y gestionar productos, carritos y ventas de manera profesional.**
