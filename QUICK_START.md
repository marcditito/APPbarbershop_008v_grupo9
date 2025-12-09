# ⚡ QUICK START - Cómo Usar Las Nuevas Funcionalidades

## 🎯 En 5 Minutos

### 1️⃣ ANIMACIÓN EN UN BOTÓN
```kotlin
import com.example.barbershopapp.utils.AnimationUtils

// En MainActivity.kt (o donde tengas tus botones):
miBoton.setOnClickListener {
    // Añade esta línea
    AnimationUtils.animateClickScale(miBoton)
    
    // Tu código existente continúa aquí
    // Toast.makeText(this, "Botón presionado", Toast.LENGTH_SHORT).show()
}
```

---

### 2️⃣ VIBRACIÓN AL HACER CLIC
```kotlin
import com.example.barbershopapp.utils.NativeDeviceUtils

miBoton.setOnClickListener {
    // Añade esta línea
    NativeDeviceUtils.vibrationClick(this)
    
    // Tu código existente continúa aquí
}
```

---

### 3️⃣ ANIMAR UN TEXTO QUE APARECE
```kotlin
import com.example.barbershopapp.utils.AnimationUtils

val miTexto = findViewById<TextView>(R.id.mi_texto)

// Para mostrarlo:
AnimationUtils.animateFadeIn(miTexto)

// Para ocultarlo:
AnimationUtils.animateFadeOut(miTexto)
```

---

### 4️⃣ LOADING CON ROTACIÓN
```kotlin
import com.example.barbershopapp.utils.AnimationUtils

val spinner = findViewById<View>(R.id.loading_spinner)

// Cuando comienza a cargar:
AnimationUtils.animateRotation(spinner)

// Cuando termina:
spinner.clearAnimation()
spinner.visibility = View.GONE
```

---

### 5️⃣ NAVEGAR A OTRA PANTALLA
```kotlin
import com.example.barbershopapp.utils.NavigationManager

miBoton.setOnClickListener {
    NavigationManager.goToCartActivity(this)
}
```

---

## 🚀 CASOS DE USO PRÁCTICOS

### Caso 1: Agregar Producto (AddProductActivity)
```kotlin
val agregarButton = findViewById<Button>(R.id.btn_agregar)
agregarButton.setOnClickListener {
    val nombre = nombreInput.text.toString()
    val precio = precioInput.text.toString()
    
    // Si validación es correcta:
    if (nombre.isNotEmpty() && precio.isNotEmpty()) {
        // Vibración de éxito
        NativeDeviceUtils.vibrationSuccess(this)
        
        // Animación del botón
        AnimationUtils.animatePulse(agregarButton)
        
        // Agregar producto (tu código existente)
        // ...
        
        // Volver al main
        Handler(Looper.getMainLooper()).postDelayed({
            NavigationManager.goToMainActivity(this)
        }, 1000)
    } else {
        // Vibración de error
        NativeDeviceUtils.vibrationError(this)
        Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
    }
}
```

---

### Caso 2: Carrito con Animación
```kotlin
val carritoButton = findViewById<Button>(R.id.btn_carrito)
carritoButton.setOnClickListener {
    // Vibración
    NativeDeviceUtils.vibrationClick(this)
    
    // Animación
    AnimationUtils.animateClickScale(carritoButton)
    
    // Navegar
    NavigationManager.goToCartActivity(this)
}

val comprarButton = findViewById<Button>(R.id.btn_comprar)
comprarButton.setOnClickListener {
    // Vibración de éxito
    NativeDeviceUtils.vibrationSuccess(this)
    
    // Animación especial
    AnimationUtils.animatePulse(comprarButton)
    
    // Mostrar mensaje
    Toast.makeText(this, "¡Compra exitosa!", Toast.LENGTH_SHORT).show()
    
    // Volver al inicio
    Handler(Looper.getMainLooper()).postDelayed({
        NavigationManager.goToMainActivity(this, clearStack = true)
    }, 1500)
}
```

---

### Caso 3: Transición Entre Vistas
```kotlin
val listView = findViewById<RecyclerView>(R.id.producto_list)
val detalleView = findViewById<View>(R.id.detalle_producto)

// Cambiar de vista con animación:
AnimationUtils.animateFadeOut(listView, 300)
Handler(Looper.getMainLooper()).postDelayed({
    listView.visibility = View.GONE
    detalleView.visibility = View.VISIBLE
    AnimationUtils.animateFadeIn(detalleView, 300)
}, 300)
```

---

### Caso 4: Entrada Animada de Lista
```kotlin
val productRecycler = findViewById<RecyclerView>(R.id.productos_recycler)

// Cuando carga la lista:
AnimationUtils.animateSlideInFromBottom(productRecycler)
```

---

### Caso 5: Notificación Visual + Háptica
```kotlin
val successMessage = findViewById<TextView>(R.id.success_msg)

fun showSuccess(message: String) {
    // Vibración
    NativeDeviceUtils.vibrationSuccess(this)
    
    // Mostrar con animación
    successMessage.text = message
    AnimationUtils.animateFadeIn(successMessage)
    
    // Ocultar después de 2 segundos
    Handler(Looper.getMainLooper()).postDelayed({
        AnimationUtils.animateFadeOut(successMessage)
    }, 2000)
}

// Usar:
showSuccess("¡Producto agregado!")
```

---

## 📦 LISTA DE FUNCIONES DISPONIBLES

### Animaciones (8 funciones)
```
AnimationUtils.animateClickScale(view)              // Presionar botón
AnimationUtils.animateFadeIn(view)                  // Aparición
AnimationUtils.animateFadeOut(view)                 // Desaparición
AnimationUtils.animateSlideInFromTop(view)          // Entrada desde arriba
AnimationUtils.animateSlideInFromBottom(view)       // Entrada desde abajo
AnimationUtils.animateRotation(view)                // Rotación/Loading
AnimationUtils.animatePulse(view)                   // Pulso
AnimationUtils.animateBounce(view)                  // Rebote
```

### Vibración (5 funciones)
```
NativeDeviceUtils.vibrateDevice(context)            // Vibración simple
NativeDeviceUtils.vibrationSuccess(context)         // Vibración éxito
NativeDeviceUtils.vibrationError(context)           // Vibración error
NativeDeviceUtils.vibrationClick(context)           // Pequeña vibración
NativeDeviceUtils.vibratePattern(context, pattern)  // Patrón personalizado
```

### Sensores (6 funciones)
```
NativeDeviceUtils.getAccelerometer(context)         // Obtener acelerómetro
NativeDeviceUtils.getProximitySensor(context)       // Obtener proximidad
NativeDeviceUtils.getLightSensor(context)           // Obtener sensor luz
NativeDeviceUtils.registerAccelerometerListener()   // Registrar listener
NativeDeviceUtils.unregisterSensorListener()        // Desregistrar
NativeDeviceUtils.isDeviceMoving(sensorEvent)       // Detectar movimiento
```

### Navegación (6 funciones)
```
NavigationManager.goToMainActivity(this)            // Ir a Main
NavigationManager.goToLoginActivity(this)           // Ir a Login
NavigationManager.goToCartActivity(this)            // Ir a Carrito
NavigationManager.goToAddProductActivity(this)      // Ir a Agregar
NavigationManager.goToProductDetailActivity()       // Ir a Detalle
NavigationManager.navigateToActivity()              // Genérico
```

---

## 🎨 EJEMPLOS COMPLETOS LISTOS PARA COPIAR

### Opción 1: Usar IntegrationExamples (MÁS FÁCIL)
```kotlin
import com.example.barbershopapp.ui.examples.IntegrationExamples

miBoton.setOnClickListener {
    // Todo en una línea:
    IntegrationExamples.completeActionFlow(
        button = miBoton,
        context = this,
        activity = this,
        onSuccess = {
            NavigationManager.goToMainActivity(this)
        }
    )
}
```

### Opción 2: Manual (MÁS CONTROL)
```kotlin
miBoton.setOnClickListener {
    // Paso 1: Vibración
    NativeDeviceUtils.vibrationClick(this)
    
    // Paso 2: Animación visual
    AnimationUtils.animateClickScale(miBoton)
    
    // Paso 3: Tu lógica de negocio
    agregarProducto()
    
    // Paso 4: Navegar
    NavigationManager.goToMainActivity(this)
}
```

---

## ⚙️ REQUISITOS

✅ **Ya completados** en build.gradle y AndroidManifest.xml

Pero si lo haces manual:
```groovy
// build.gradle
def nav_version = "2.8.5"
implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
```

```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.SENSOR" />
```

---

## 🐛 DEBUGGING

### Ver logs de todas las operaciones
```kotlin
// Los logs están en LogCat con estos tags:
"AnimationUtils"        // Animaciones
"NativeDeviceUtils"     // Vibración/Sensores
"NavigationManager"     // Navegación
"IntegrationExamples"   // Ejemplos
```

### Verificar si sensores disponibles
```kotlin
val accel = NativeDeviceUtils.getAccelerometer(context)
if (accel != null) {
    Log.d("APP", "Acelerómetro: ${accel.name}")
} else {
    Log.d("APP", "Acelerómetro no disponible")
}
```

---

## ❓ PREGUNTAS FRECUENTES

**P: ¿Necesito cambiar mi código existente?**  
R: NO. Todo es completamente opcional y modular.

**P: ¿Qué pasa si no tengo permiso de vibración?**  
R: La función no hace nada pero la app continúa funcionando.

**P: ¿Puedo usar varias funciones juntas?**  
R: Sí, absolutamente. Combina animación + vibración + navegación.

**P: ¿Esto afecta el rendimiento?**  
R: NO. Las animaciones son muy livianas y nativas de Android.

**P: ¿Funciona en dispositivos viejos?**  
R: Sí. Compatible desde Android API 21 (incluso más atrás).

---

## 📞 SOPORTE

Si algo no funciona:
1. Sincroniza Gradle: `./gradlew clean build`
2. Revisa los logs en LogCat
3. Verifica que importaste correctamente
4. Revisa que el contexto es válido

---

**¡Listo para empezar! Copia y pega los ejemplos en tu código.** 🚀

