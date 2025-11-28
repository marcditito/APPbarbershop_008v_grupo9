# INSTRUCCIONES PARA MIGRAR DE KAPT A KSP

## ¿Por qué migrar a KSP?

- ⚡ **2x más rápido** que KAPT
- ✅ **Sin problemas** con JDK 17+ (no necesita --add-exports)
- 🎯 **Mejor integrado** con Kotlin 2.x
- 🔧 **Recomendado por Google** para nuevos proyectos

---

## PASO 1: Actualizar build.gradle (Project)

**Archivo**: `build.gradle` (raíz del proyecto)

```groovy
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:8.7.3'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21"
        // ⭐ AGREGAR ESTA LÍNEA:
        classpath "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.21-1.0.29"
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete) {
    delete(layout.buildDirectory)
}
```

---

## PASO 2: Actualizar app/build.gradle

**Archivo**: `app/build.gradle`

### 2.1 Cambiar plugins:

```groovy
plugins {
    id "com.android.application"
    id "org.jetbrains.kotlin.android"
    // ❌ ELIMINAR: id "kotlin-kapt"
    // ✅ AGREGAR:
    id "com.google.devtools.ksp"
    id "kotlin-parcelize"
}
```

### 2.2 ELIMINAR el bloque kapt completo:

```groovy
// ❌ ELIMINAR TODO ESTE BLOQUE:
/*
kapt {
    correctErrorTypes = true
    useBuildCache = true
    javacOptions {
        option("-Xmaxerrs", 500)
        // ... etc
    }
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
        // ... etc
    }
}
*/
```

### 2.3 AGREGAR configuración de KSP:

```groovy
// ✅ AGREGAR DESPUÉS DEL BLOQUE kotlin { }:
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
    arg("room.expandProjection", "true")
}
```

### 2.4 Cambiar dependencias:

```groovy
dependencies {
    implementation "androidx.core:core-ktx:1.15.0"
    implementation "androidx.appcompat:appcompat:1.7.0"
    implementation "androidx.activity:activity-ktx:1.9.3"
    implementation "androidx.fragment:fragment-ktx:1.8.5"

    // Material Design
    implementation "com.google.android.material:material:1.12.0"

    // ConstraintLayout
    implementation "androidx.constraintlayout:constraintlayout:2.2.0"

    // ViewModel y LiveData
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.8.7"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.8.7"

    // Room
    implementation "androidx.room:room-runtime:2.6.1"
    implementation "androidx.room:room-ktx:2.6.1"
    // ❌ CAMBIAR: kapt "androidx.room:room-compiler:2.6.1"
    // ✅ POR: 
    ksp "androidx.room:room-compiler:2.6.1"

    // Play Services Location
    implementation "com.google.android.gms:play-services-location:21.3.0"

    // Corrutinas
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.9.0"

    // RecyclerView
    implementation "androidx.recyclerview:recyclerview:1.3.2"

    // Biometric Authentication
    implementation "androidx.biometric:biometric:1.2.0-alpha05"

    // Testing
    testImplementation "junit:junit:4.13.2"
    androidTestImplementation "androidx.test.ext:junit:1.2.1"
    androidTestImplementation "androidx.test.espresso:espresso-core:3.6.1"
}
```

---

## PASO 3: Actualizar gradle.properties

**Archivo**: `gradle.properties`

```properties
android.useAndroidX=true
android.enableJetifier=true

# Kotlin Configuration
kotlin.incremental=true
kotlin.incremental.multiplatform=true

# ❌ ELIMINAR configuraciones de KAPT:
# kapt.use.worker.api=true
# kapt.incremental.apt=true
# kapt.include.compile.classpath=false

# ✅ AGREGAR configuraciones de KSP:
ksp.incremental=true
ksp.incremental.log=true

# Gradle Configuration (SIMPLIFICADA - ya no necesitas --add-exports)
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configureondemand=true
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m -XX:+HeapDumpOnOutOfMemoryError

# AndroidX DataBinding y ViewBinding
android.databinding.incremental=true
android.databinding.enableV2=true
```

---

## PASO 4: Limpiar y Reconstruir

### Opción A: Usar script automatizado
```cmd
REBUILD_KOTLIN2.bat
```

### Opción B: Comandos manuales
```powershell
# 1. Detener Gradle
.\gradlew --stop

# 2. Limpiar todo
Remove-Item -Path "app\build" -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -Path "build" -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -Path ".gradle" -Recurse -Force -ErrorAction SilentlyContinue

# 3. Sincronizar
.\gradlew clean --refresh-dependencies

# 4. Compilar
.\gradlew build
```

### Opción C: En Android Studio
1. **File → Invalidate Caches / Restart → Invalidate and Restart**
2. Esperar a que se reinicie
3. **Build → Rebuild Project**

---

## PASO 5: Verificar cambios en el código (generalmente NO necesario)

Room genera archivos con KSP en rutas ligeramente diferentes, pero generalmente el código sigue funcionando sin cambios.

Si tienes errores de importación, verifica:

```kotlin
// Antes (KAPT generaba en java/generated/...)
import com.example.barbershopapp.data.AppDatabase_Impl

// Después (KSP genera en kotlin/...)
// La importación debería ser la misma, pero puede estar en diferente paquete
// Android Studio lo detecta automáticamente
```

---

## 📊 Comparación KAPT vs KSP

| Característica | KAPT | KSP |
|---|---|---|
| Velocidad de compilación | Lento | 2x más rápido |
| Compatibilidad JDK 17+ | Requiere --add-exports | Funciona nativamente |
| Soporte Kotlin 2.x | Limitado | Completo |
| Recomendado por Google | No | ✅ Sí |
| Configuración | Compleja | Simple |

---

## ⚠️ Notas Importantes

1. **Room es compatible con KSP desde la versión 2.4.0+** (usamos 2.6.1 ✅)
2. **DataBinding NO soporta KSP** (sigue usando el compilador de Java, pero no necesita KAPT)
3. **ViewBinding NO necesita procesamiento de anotaciones** (funciona directamente)
4. Si usas otras librerías con KAPT, verifica si soportan KSP:
   - Hilt/Dagger: ✅ Soporta KSP
   - Moshi: ✅ Soporta KSP
   - Glide: ✅ Soporta KSP
   - Parcelize: ✅ No necesita procesamiento (es plugin de Kotlin)

---

## 🔍 Solución de Problemas

### Error: "Cannot find symbol" en clases generadas por Room

**Solución**: Limpiar proyecto
```powershell
.\gradlew clean
.\gradlew build
```

### Error: "Duplicate class found"

**Solución**: Asegúrate de haber eliminado COMPLETAMENTE el plugin `kotlin-kapt` y todas las referencias a `kapt` en dependencies.

### Compilación más lenta después de migrar

**Solución**: Primera compilación con KSP puede ser lenta, las siguientes serán mucho más rápidas debido a la compilación incremental.

---

## 📚 Referencias

- [KSP Official Documentation](https://kotlinlang.org/docs/ksp-overview.html)
- [Migrating Room to KSP](https://developer.android.com/build/migrate-to-ksp)
- [KSP Quickstart](https://kotlinlang.org/docs/ksp-quickstart.html)

---

**Beneficio Final**: Después de migrar a KSP, tu proyecto compilará aproximadamente **2 veces más rápido** y no tendrás problemas de compatibilidad con futuras versiones de JDK o Kotlin.

