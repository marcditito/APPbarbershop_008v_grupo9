# 📱 BarberShop App - Aplicación de Barbería

Una aplicación Android moderna para gestión de productos y servicios de barbería, conectada con API Xano para backend.

## 🚀 Características Principales

### ✅ **Funcionalidades Implementadas:**
- 🔐 **Login con credenciales estáticas**
- 📋 **Gestión de productos** (CRUD completo)
- 🛒 **Carrito de compras funcional**
- 💳 **Proceso de compra simplificado**
- 🌐 **Integración con API Xano**
- 📱 **Interfaz moderna Material Design**

### 🏪 **Flujo de la Aplicación:**
1. **Pantalla de Login** - Credenciales fijas para pruebas
2. **Pantalla Principal** - Lista de productos/servicios
3. **Agregar al Carrito** - Sistema de carrito local
4. **Ver Carrito** - Gestión de items y cantidades
5. **Comprar** - Proceso de checkout con confirmación

## 🔑 **Credenciales de Acceso**

```
Email: axxelduran342@gmail.com
Password: axelduran8
```

## 🌐 **APIs Configuradas**

### **API de Productos (Xano):**
```
Base URL: https://x8ki-letl-twmt.n7.xano.io/api:W-lgR84A/
```

### **Endpoints Disponibles:**
- `GET /product` - Obtener todos los productos
- `GET /product/{id}` - Obtener producto específico  
- `POST /product` - Crear nuevo producto
- `PUT /product/{id}` - Actualizar producto
- `DELETE /product/{id}` - Eliminar producto

### **Token de Autenticación:**
```
eyJhbGciOiJBMjU2S1ciLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiemlwIjoiREVGIn0.QCYGCNO55Wf46JdZPxnv2GFv426sajTZTT6fTQ3_AtOahkFxFmwNiPyTWY-gpB5dO8-MlvY4c6JEh9RaPxTJa4IWImmhoH9M.pDlUgukCjYcqbb3vVNkAjg.KnksgpZWpFwQuNMKKk-xpQM4UKjG_hHzAHQJysWPpEuHG2avHycH_jgP0lqKxkd2AMFHwl6DRHRl16puMDsWXKGMlbuAouxELgkb9-hKfuaJJZpXMPV-vGrMsm3P4DmmkiZhuIKZquONE-xVn6u70nn03k6zCS5Zbnq7iI5G6y8.EisdYuJia-m3fRXBj1_8DCnpwbSRyu6W-v_IcIGoFqI
```

## 🛠️ **Instalación y Ejecución**

### **Opción 1: Script Automático**
```bash
# Ejecuta el script
.\START_APP.bat
```

### **Opción 2: Manual**
```bash
# Limpiar y compilar
.\gradlew clean assembleDebug

# Instalar en dispositivo/emulador
.\gradlew installDebug
```

## 📋 **Prerrequisitos**

1. **Android Studio** instalado
2. **Emulador Android** configurado O dispositivo físico
3. **Depuración USB** habilitada (para dispositivo físico)
4. **Java 11+** instalado
5. **Conexión a internet** para API calls

## 🏗️ **Arquitectura del Proyecto**

```
app/src/main/java/com/example/barbershopapp/
├── data/
│   ├── api/          # Servicios Retrofit y NetworkModule
│   └── repository/   # Repositorios para datos
├── model/           # Data classes (Product, CartItem)
├── ui/              # Activities y Adapters
├── viewmodel/       # ViewModels (ProductViewModel, CartViewModel)
└── utils/           # Utilidades
```

## 🎨 **Componentes Clave**

### **Modelos Principales:**
- `Product` - Modelo de producto/servicio
- `CartItem` - Item del carrito con cantidad

### **ViewModels:**
- `ProductViewModel` - Gestión de productos y API calls
- `CartViewModel` - Gestión del carrito de compras

### **Activities:**
- `LoginActivity` - Pantalla de inicio de sesión
- `MainActivity` - Pantalla principal con productos
- `AddProductActivity` - Agregar nuevos productos
- `CartActivity` - Ver y gestionar carrito

## 🛍️ **Productos Demo Incluidos**

La app incluye productos de demostración:
- Corte Clásico ($25)
- Corte Moderno ($30) 
- Barba Completa ($20)
- Combo Corte + Barba ($40)
- Corte Fade ($28)
- Bigote Styling ($15)
- Lavado + Corte ($35)
- Afeitado Clásico ($22)

## 🔧 **Configuración de Red**

La aplicación está configurada para:
- **Timeout de conexión:** 30 segundos
- **Logging HTTP:** Completo (para debugging)
- **Reintentos automáticos** en caso de error de red
- **Productos demo** como fallback si no hay conectividad

## 📱 **Flujo de Usuario**

1. **Login** con credenciales fijas
2. **Ver productos** en la pantalla principal
3. **Agregar productos** al carrito
4. **Ver carrito** con items agregados  
5. **Modificar cantidades** en el carrito
6. **Realizar compra** con mensaje de confirmación

## ⚡ **Funcionalidades Destacadas**

- **Productos dinámicos** desde API Xano
- **Carrito persistente** durante la sesión
- **UI responsiva** Material Design
- **Manejo de errores** robusto
- **Loading states** en operaciones async
- **Auto-sync** con backend

## 📞 **Soporte y Debugging**

Para problemas comunes:

1. **Error de compilación:** Ejecutar `.\gradlew clean`
2. **No encuentra emulador:** Verificar AVD Manager en Android Studio
3. **Error de red:** Verificar conexión a internet
4. **Login no funciona:** Usar credenciales exactas proporcionadas

## 🔄 **Próximas Mejoras**

- [ ] Integración completa con autenticación Xano
- [ ] Imágenes de productos
- [ ] Historial de compras
- [ ] Notificaciones push
- [ ] Modo offline
- [ ] Favoritos de usuario

---

**Desarrollado por:** GitHub Copilot  
**Fecha:** Diciembre 2024  
**Versión:** 1.0.0
