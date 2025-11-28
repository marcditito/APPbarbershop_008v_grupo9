@echo off
cls
echo.
echo ═══════════════════════════════════════════════════════════════
echo  INSTALACIÓN LIMPIA COMPLETA - BARBERSHOP APP
echo ═══════════════════════════════════════════════════════════════
echo.
echo Este script va a:
echo  1. Detener todos los procesos de Gradle
echo  2. Eliminar TODOS los archivos compilados
echo  3. Limpiar cache de Android Studio
echo  4. Recompilar desde CERO
echo  5. Desinstalar app del dispositivo (usando ADB)
echo  6. Instalar version nueva
echo.
pause
echo.

echo [PASO 1/7] Deteniendo Gradle daemon...
call gradlew --stop
timeout /t 2 /nobreak >nul
echo ✅ Gradle detenido
echo.

echo [PASO 2/7] Limpiando proyecto con gradlew clean...
call gradlew clean
echo ✅ Clean ejecutado
echo.

echo [PASO 3/7] Eliminando carpetas de build...
if exist "app\build" (
    echo    Eliminando app\build...
    rmdir /s /q "app\build"
    echo    ✅ app\build eliminado
)
if exist ".gradle" (
    echo    Eliminando .gradle...
    rmdir /s /q ".gradle"
    echo    ✅ .gradle eliminado
)
if exist "build" (
    echo    Eliminando build raiz...
    rmdir /s /q "build"
    echo    ✅ build eliminado
)
if exist ".idea" (
    echo    Eliminando .idea...
    rmdir /s /q ".idea"
    echo    ✅ .idea eliminado
)
echo.

echo [PASO 4/7] Eliminando archivos locales de compilación...
if exist "local.properties" (
    del /f /q "local.properties"
    echo ✅ local.properties eliminado
)
echo.

echo [PASO 5/7] Desinstalando app del dispositivo...
adb uninstall com.example.barbershopapp 2>nul
if %errorlevel%==0 (
    echo ✅ App desinstalada del dispositivo
) else (
    echo ⚠️  App no estaba instalada o dispositivo no conectado
)
echo.

echo [PASO 6/7] Recompilando proyecto COMPLETO desde cero...
echo (Esto puede tardar 2-3 minutos)
call gradlew assembleDebug --rerun-tasks
if %errorlevel%==0 (
    echo ✅ Compilación exitosa
) else (
    echo ❌ Error en compilación
    pause
    exit /b 1
)
echo.

echo [PASO 7/7] Instalando APK en dispositivo...
adb install -r app\build\outputs\apk\debug\app-debug.apk
if %errorlevel%==0 (
    echo ✅ App instalada correctamente
) else (
    echo ⚠️  No se pudo instalar automáticamente
    echo    Por favor instala manualmente desde Android Studio
)
echo.

echo ═══════════════════════════════════════════════════════════════
echo  ✅ INSTALACIÓN LIMPIA COMPLETADA
echo ═══════════════════════════════════════════════════════════════
echo.
echo PRÓXIMOS PASOS:
echo  1. Abre la app en el dispositivo/emulador
echo  2. Inicia sesión con:
echo     Email: demo@barbershop.com
echo     Password: 123456
echo  3. Deberías ver el diseño MODERNO con:
echo     - Card "Tienda de Barbería"
echo     - Botón carrito azul
echo     - Grid de productos 2x2
echo     - FAB "Agregar Producto"
echo.
echo Si aún ves el diseño antiguo:
echo  - Cierra COMPLETAMENTE la app
echo  - Abre Android Studio
echo  - File → Invalidate Caches / Restart
echo  - Espera a que reinicie
echo  - Vuelve a ejecutar este script
echo.
pause

