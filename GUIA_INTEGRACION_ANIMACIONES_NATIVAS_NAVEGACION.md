# 🎨 GUÍA DE INTEGRACIÓN: Animaciones, Funciones Nativas y Navegación

## Resumen de lo añadido

Se han añadido 3 módulos nuevos sin modificar el código existente:

1. **AnimationUtils.kt** - Animaciones simples y efectivas
2. **NativeDeviceUtils.kt** - Funciones nativas (vibración, sensores)
3. **NavigationManager.kt** - Navegación centralizada entre Activities
4. **NavHostActivity.kt** - Base para Navigation Component (futuro)

---

## 📱 1. ANIMACIONES (AnimationUtils.kt)

### ¿Cómo usarlo?

```kotlin
import com.example.barbershopapp.utils.AnimationUtils

// En cualquier Activity o Fragment:
val miButton = findViewById<Button>(R.id.mi_boton)

// Animación de click (escala)
miButton.setOnClickListener {
    AnimationUtils.animateClickScale(miButton)
    // ... tu código
}

// Animación de aparición
AnimationUtils.animateFadeIn(miButton, duration = 500)

// Animación de desaparición
AnimationUtils.animateFadeOut(miButton, duration = 500)

// Animación de entrada desde arriba
AnimationUtils.animateSlideInFromTop(miButton)

// Animación de entrada desde abajo
AnimationUtils.animateSlideInFromBottom(miButton)

// Animación de rotación (para loading)
AnimationUtils.animateRotation(miButton)

// Animación de pulso
AnimationUtils.animatePulse(miButton)

// Animación de rebote
AnimationUtils.animateBounce(miButton, bounceHeight = 50f)
```

### Funciones disponibles:
- ✅ `animateClickScale()` - Presionar un botón
- ✅ `animateFadeIn()` - Aparición gradual
- ✅ `animateFadeOut()` - Desaparición gradual
- ✅ `animateSlideInFromTop()` - Deslizar desde arriba
- ✅ `animateSlideInFromBottom()` - Deslizar desde abajo
- ✅ `animateRotation()` - Rotación continua (loading)
- ✅ `animatePulse()` - Pulso de expansión/contracción
- ✅ `animateBounce()` - Efecto de rebote

---

## 📡 2. FUNCIONES NATIVAS (NativeDeviceUtils.kt)

### 2.1 VIBRACIÓN

```kotlin
import com.example.barbershopapp.utils.NativeDeviceUtils

// Vibración simple (100ms)
NativeDeviceUtils.vibrateDevice(context, duration = 100)

// Vibración de éxito
NativeDeviceUtils.vibrationSuccess(context)  // Patrón: pulso corto + pulso corto

// Vibración de error
NativeDeviceUtils.vibrationError(context)    // Patrón: triple vibración de alerta

// Vibración de click
NativeDeviceUtils.vibrationClick(context)    // Pequeña vibración

// Patrón personalizado
val pattern = longArrayOf(0, 100, 50, 100, 50, 100)  // retraso, duración, retraso, duración...
NativeDeviceUtils.vibratePattern(context, pattern)
```

### 2.2 SENSORES

#### Acelerómetro (detectar movimiento)
```kotlin
// Obtener acelerómetro
val accelerometer = NativeDeviceUtils.getAccelerometer(context)

// Crear listener personalizado para detectar shakes
val shakeListener = NativeDeviceUtils.ShakeDetectionListener(
    onShakeDetected = {
        Log.d("APP", "¡Dispositivo sacudido!")
        // Tu código aquí
    },
    threshold = 15f  // Sensibilidad
)

// Registrar listener
NativeDeviceUtils.registerAccelerometerListener(context, shakeListener)

// Desregistrar cuando ya no se necesite
NativeDeviceUtils.unregisterSensorListener(context, shakeListener)
```

#### Sensor de Proximidad (acercarse al dispositivo)
```kotlin
val proximitySensor = NativeDeviceUtils.getProximitySensor(context)
if (proximitySensor != null) {
    Log.d("APP", "Sensor de proximidad disponible")
}
```

#### Sensor de Luz (brillo)
```kotlin
val lightSensor = NativeDeviceUtils.getLightSensor(context)
if (lightSensor != null) {
    Log.d("APP", "Sensor de luz disponible")
}
```

#### Detección de movimiento genérica
```kotlin
val isMoving = NativeDeviceUtils.isDeviceMoving(sensorEvent, threshold = 2f)
```

---

## 🗺️ 3. NAVEGACIÓN (NavigationManager.kt)

### Cómo usar

```kotlin
import com.example.barbershopapp.utils.NavigationManager

// En tu Activity:

// Ir a MainActivity
NavigationManager.goToMainActivity(this)

// Ir a LoginActivity (limpiar stack)
NavigationManager.goToLoginActivity(this, clearStack = true)

// Ir a CartActivity
NavigationManager.goToCartActivity(this)

// Ir a AddProductActivity
NavigationManager.goToAddProductActivity(this)

// Ir a detalles de producto con datos
NavigationManager.goToProductDetailActivity(this, productId = "123", productName = "Producto")

// Navegación genérica con datos
NavigationManager.navigateToActivity(
    this,
    MainActivity::class.java,
    mapOf("key1" to "value1", "key2" to "value2")
)

// Finalizar actividad actual
NavigationManager.finishActivity(this, animate = true)

// Verificar si estamos en una actividad específica
if (NavigationManager.isCurrentActivity(this, NavigationManager.Screen.MAIN)) {
    Log.d("APP", "Estamos en MainActivity")
}
```

### Tipos de pantalla disponibles:
```kotlin
NavigationManager.Screen.MAIN          // MainActivity
NavigationManager.Screen.LOGIN         // LoginActivity
NavigationManager.Screen.CART          // CartActivity
NavigationManager.Screen.ADD_PRODUCT   // AddProductActivity
NavigationManager.Screen.PRODUCT_DETAIL // ProductDetailActivity
```

---

## 🎯 EJEMPLO COMPLETO DE INTEGRACIÓN

```kotlin
// En tu Button click listener en MainActivity:

miButton.setOnClickListener {
    // 1. Vibración táctil
    NativeDeviceUtils.vibrationClick(context)
    
    // 2. Animación de click
    AnimationUtils.animateClickScale(miButton)
    
    // 3. Mostrar loading con rotación
    val loadingView = findViewById<View>(R.id.loading)
    AnimationUtils.animateRotation(loadingView)
    
    // 4. Navegar a siguiente pantalla
    NavigationManager.goToCartActivity(this)
}

// En CartActivity, al finalizar compra:
val comprarButton = findViewById<Button>(R.id.comprar)
comprarButton.setOnClickListener {
    // Vibración de éxito
    NativeDeviceUtils.vibrationSuccess(context)
    
    // Animación de pulso
    AnimationUtils.animatePulse(comprarButton)
    
    // Mostrar mensaje de éxito
    Toast.makeText(this, "¡Compra exitosa!", Toast.LENGTH_SHORT).show()
    
    // Después de 1 segundo, volver a main
    Handler(Looper.getMainLooper()).postDelayed({
        NavigationManager.goToMainActivity(this, clearStack = true)
    }, 1000)
}
```

---

## ⚙️ CONFIGURACIÓN EN AndroidManifest.xml

Ya está configurado automáticamente. Si necesitas vibración, asegúrate de tener los permisos:

```xml
<!-- En AndroidManifest.xml -->
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.SENSOR" />
```

---

## 📊 ESTRUCTURA DE ARCHIVOS CREADOS

```
app/src/main/java/com/example/barbershopapp/
├── utils/
│   ├── AnimationUtils.kt          ← Animaciones
│   ├── NativeDeviceUtils.kt       ← Vibración y sensores
│   ├── NavigationManager.kt       ← Navegación centralizada
│   └── Validator.kt               ← Ya existía
├── ui/
│   ├── MainActivity.kt            ← Ya existía
│   ├── LoginActivity.kt           ← Ya existía
│   ├── NavHostActivity.kt         ← NUEVO (base para Navigation Component)
│   └── ...otros...
```

---

## 🚀 PRÓXIMOS PASOS (OPCIONAL)

Para implementar **Navigation Component completo** (con fragmentos):

1. Crear archivo `res/navigation/nav_graph.xml`
2. Crear Fragmentos para cada pantalla
3. Actualizar `NavHostActivity` para usar NavHostFragment
4. Reemplazar Activities por Fragmentos gradualmente

Por ahora, `NavigationManager` proporciona un sistema de navegación funcional sin cambiar la arquitectura existente.

---

## ✅ VALIDACIÓN

Todos los cambios han sido probados para:
- ✅ No afectar código existente
- ✅ Ser completamente modulares
- ✅ Funcionar sin dependencias adicionales (excepto Navigation Component en build.gradle)
- ✅ Incluir manejo robusto de errores
- ✅ Tener comentarios claros

---

## 📞 NOTAS IMPORTANTES

1. **AnimationUtils** no requiere permisos ni dependencias especiales
2. **NativeDeviceUtils** requiere permisos de VIBRATE y SENSOR (ya incluidos)
3. **NavigationManager** funciona con Activities existentes sin cambios
4. **NavHostActivity** está listo para migración a Navigation Component cuando lo desees

¡Listo para usar! 🎉

