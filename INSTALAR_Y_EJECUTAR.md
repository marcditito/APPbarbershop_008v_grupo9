# 🚀 INSTRUCCIONES - INSTALAR Y EJECUTAR LA APP

## 📱 APK GENERADO

```
Ubicación: C:\Users\duran\OneDrive\Escritorio\BarberShopApp\BarberShopApp\app\build\outputs\apk\debug\app-debug.apk

Tamaño: 9.5 MB
Versión: debug
API mínima: 21 (Android 5.0+)
Estado: ✅ Listo para instalar
```

---

## 🔧 OPCIÓN 1: Ejecutar en Emulador (Android Studio)

### Paso 1: Verificar emulador
```bash
# Abrir Android Studio
# Ir a: AVD Manager
# Verificar que hay al menos un emulador creado y disponible
# Si no hay, crear uno:
#   - Archivo → Virtual Device Manager
#   - Crear Pixel 6 con API 34 (mínimo API 21)
```

### Paso 2: Ejecutar desde Android Studio
```bash
# Opción A: Botón Play (verde) en Android Studio
# Opción B: En terminal:
cd C:\Users\duran\OneDrive\Escritorio\BarberShopApp\BarberShopApp
.\gradlew.bat installDebug
```

### Paso 3: Esperar instalación
```
Se instalará automáticamente en el emulador conectado
La app se abrirá automáticamente
```

---

## 🔧 OPCIÓN 2: Ejecutar en Dispositivo Real

### Paso 1: Preparar dispositivo
```
1. Conectar teléfono/tablet a la computadora con USB
2. Activar "Depuración de USB" en Configuración del dispositivo:
   - Configuración → Acerca de
   - Tocar "Número de compilación" 7 veces
   - Volver atrás y entrar a "Opciones de desarrollador"
   - Activar "Depuración de USB"
3. Aceptar permiso de depuración cuando aparezca en el dispositivo
```

### Paso 2: Verificar conexión
```bash
cd C:\Users\duran\OneDrive\Escritorio\BarberShopApp\BarberShopApp

# Verificar que el dispositivo está conectado:
adb devices
# Debería mostrar tu dispositivo como "device"
```

### Paso 3: Instalar APK
```bash
# Opción A: Desde terminal
adb install app\build\outputs\apk\debug\app-debug.apk

# Opción B: Dragging and dropping
# Arrastrar el APK directamente al emulador

# Opción C: Hacer doble click en el APK (en emulador con depuración USB)
```

### Paso 4: Abrir app
```
Ir a Aplicaciones → Buscar "BarberShop" → Tocar para abrir
```

---

## 🔧 OPCIÓN 3: Instalar Manualmente el APK

### Paso 1: Copiar APK
```bash
# Copiar archivo
copy "C:\Users\duran\OneDrive\Escritorio\BarberShopApp\BarberShopApp\app\build\outputs\apk\debug\app-debug.apk" "C:\Users\tuUsuario\Desktop\BarberShop.apk"

# O usar explorador de archivos:
# Navegar a: C:\Users\duran\OneDrive\Escritorio\BarberShopApp\BarberShopApp\app\build\outputs\apk\debug\
# Click derecho en app-debug.apk → Copiar
# Pegar en Descargas del teléfono
```

### Paso 2: Instalar desde teléfono
```
1. Abrir administrador de archivos en teléfono
2. Navegar a Descargas
3. Tocar en app-debug.apk
4. Confirmar instalación
5. Abrir app después de instalar
```

---

## ✅ VERIFICACIÓN - ¿LA APP ESTÁ FUNCIONANDO?

### Prueba 1: Animaciones
```
[ ] Presiona cualquier botón
[ ] El botón se anima visiblemente
[ ] Se siente vibración en el dispositivo
[ ] Acción se ejecuta correctamente
```

### Prueba 2: Navegación
```
[ ] Botones inferiores responden
[ ] Pantalla cambia al presionar
[ ] Animación de deslizamiento visible
[ ] Puedes volver atrás
```

### Prueba 3: Agregar Producto
```
[ ] Presiona ➕ AGREGAR en un producto
[ ] Botón hace efecto de salto (bounce)
[ ] Sientes vibración doble
[ ] Toast de confirmación aparece
[ ] Carrito se actualiza
```

### Prueba 4: Sensores (Lo especial)
```
[ ] Sacude el teléfono con fuerza
[ ] Se detecta la sacudida
[ ] Botón carrito hace pulso
[ ] Sientes vibración especial
[ ] Toast muestra: "¡Carrito refrescado!"
[ ] Carrito se actualiza
```

---

## 🐛 SOLUCIONAR PROBLEMAS

### Problema: "APK no se instala"
```
Solución:
1. Asegurar que API es 21+ en el dispositivo
2. Permitir instalación de fuentes desconocidas:
   Configuración → Seguridad → Fuentes desconocidas → ON
3. Intentar desinstalar versión anterior:
   adb uninstall com.example.barbershopapp
4. Volver a instalar
```

### Problema: "La app se cierra inmediatamente"
```
Solución:
1. Limpiar caché:
   - Configuración → Aplicaciones → BarberShop → Borrar caché
   - Configuración → Aplicaciones → BarberShop → Borrar datos
2. Desinstalar y reinstalar
3. Revisar logs en Android Studio:
   - Logcat → buscar errores
```

### Problema: "No se siente vibración"
```
Solución:
1. Verificar que vibración está activada:
   - Configuración → Sonido → Vibración → ON
2. Aumentar volumen del dispositivo
3. Revisar si aplicación tiene permiso de vibración
```

### Problema: "No se detecta sacudida"
```
Solución:
1. Sacudir más fuerte
2. Revisar que acelerómetro está activado:
   - Configuración → Sensores → Acelerómetro ON
3. Revisar logs para ver si sensor se inicializó:
   - Abrir Android Studio Logcat
   - Buscar "Acelerómetro iniciado"
```

---

## 📊 INFORMACIÓN DEL DISPOSITIVO

### Requisitos Mínimos
```
API mínima:  21 (Android 5.0)
API objetivo: 34 (Android 14)
RAM mínima:   512 MB
Espacio:      50 MB
```

### Permisos Requeridos
```
✓ INTERNET (ya está)
✓ VIBRATE (agregado)
✓ SENSOR (agregado)
✓ CAMERA (opcional)
✓ WRITE_EXTERNAL_STORAGE (opcional)
```

### Sensores Detectados
```
✓ Acelerómetro (FUNCIONANDO)
  - Detecta sacudidas
  - Umbral: 30 m/s²
  - Delay: 500ms

○ Proximidad (PREPARADO)
  - Para futuras características
  - No activo aún

○ Luz (PREPARADO)
  - Para futuras características
  - No activo aún
```

---

## 🎯 FLUJO DE PRUEBA RECOMENDADO

### 1. Abrir la app
```
Esperado: Pantalla con productos, botones funcionales
```

### 2. Presionar botón cualquiera
```
Esperado:
- Botón se anima
- Vibración corta
- Acción se ejecuta
```

### 3. Agregar un producto
```
Esperado:
- Botón salta (bounce)
- Vibración doble
- Toast: "✅ Producto agregado"
- Carrito +1 item
```

### 4. Navegar entre botones
```
Esperado:
- Botones se deslizan
- Pantalla cambia
- Sin lag
- Vibración en cada click
```

### 5. Sacudir el teléfono
```
Esperado:
- Se detecta sacudida automáticamente
- Botón carrito pulsa
- Vibración especial
- Toast: "🔄 ¡Carrito refrescado!"
- Carrito se actualiza
```

---

## 📋 DEBUGGING EN ANDROID STUDIO

### Ver logs en tiempo real
```
1. Abiir Android Studio
2. Ir a: View → Tool Windows → Logcat
3. En el filtro, escribir: "MainActivity"
4. Ejecutar la app
5. Ver mensajes en tiempo real

Buscar mensajes como:
- "🎉 ¡SACUDIDA DETECTADA!"
- "✅ Sensores nativos inicializados"
- "Navegando a..."
```

### Romper en un punto específico
```
1. Click en número de línea (izquierda) en MainActivity.kt
2. Punto rojo aparece (breakpoint)
3. Ejecutar app en Debug mode (Shift+F9)
4. App pausa en ese punto
5. Inspeccionar variables y continuar paso a paso
```

---

## 📱 EMULADORES RECOMENDADOS

### Para mejor rendimiento
```
Pixel 6 API 34
- Resolución: 1080 x 2340
- RAM: 4GB (mínimo)
- Almacenamiento: 6GB

O

Pixel 4 API 30
- Resolución: 1080 x 2280
- RAM: 2GB
- Almacenamiento: 4GB
```

### Crear nuevo emulador
```
1. Android Studio → AVD Manager
2. Click "Create Virtual Device"
3. Seleccionar "Pixel 6"
4. Seleccionar API 34 (o 30+)
5. Asignar RAM: 4GB
6. Click Finish
```

---

## 🎊 ¡LISTO!

Una vez que la app esté corriendo:

✅ Verás animaciones en cada acción
✅ Sentirás vibración al presionar botones
✅ Podrás navegar fluidamente
✅ Podrás "sacudir" para refrescar carrito
✅ Todo funcionará sin errores

¡Disfruta probando las nuevas funcionalidades! 🚀

---

## 📞 REFERENCIAS

- APK: `app/build/outputs/apk/debug/app-debug.apk`
- Documentación: Ver otros .md en la carpeta principal
- Código fuente: `app/src/main/java/com/example/barbershopapp/`


