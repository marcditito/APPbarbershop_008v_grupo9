@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul
color 0A
cls

echo.
echo ════════════════════════════════════════════════════════════════════
echo              RECONSTRUCCION COMPLETA - BARBER SHOP APP
echo ════════════════════════════════════════════════════════════════════
echo.
echo Este script va a:
echo   1. Detener Gradle daemon
echo   2. Eliminar carpetas de build
echo   3. Limpiar el proyecto
echo   4. Sincronizar dependencias
echo   5. Compilar la app
echo.
echo Esto puede tardar 5-15 minutos...
echo.
pause

cd /d "%~dp0"

echo.
echo [1/6] Deteniendo Gradle daemon...
call gradlew.bat --stop
timeout /t 2 /nobreak >nul
echo ✓ Completado

echo.
echo [2/6] Eliminando carpeta .gradle...
if exist ".gradle" (
    rmdir /s /q ".gradle" 2>nul
    echo ✓ Carpeta .gradle eliminada
) else (
    echo - No existe (OK)
)

echo.
echo [3/6] Eliminando carpeta build (raíz)...
if exist "build" (
    rmdir /s /q "build" 2>nul
    echo ✓ Carpeta build eliminada
) else (
    echo - No existe (OK)
)

echo.
echo [4/6] Eliminando carpeta app\build...
if exist "app\build" (
    rmdir /s /q "app\build" 2>nul
    echo ✓ Carpeta app\build eliminada
) else (
    echo - No existe (OK)
)

echo.
echo [5/6] Ejecutando gradle clean...
call gradlew.bat clean
if !errorlevel! neq 0 (
    echo.
    echo ✗ ERROR: Gradle clean falló
    echo Revisa los mensajes anteriores.
    pause
    exit /b 1
)
echo ✓ Completado

echo.
echo [6/6] Compilando APK debug...
echo (Esto puede tardar varios minutos la primera vez)
echo.
call gradlew.bat assembleDebug
if !errorlevel! neq 0 (
    echo.
    echo ✗ ERROR: La compilación falló
    echo.
    echo Revisa los errores anteriores.
    echo.
    pause
    exit /b 1
)

echo.
echo ════════════════════════════════════════════════════════════════════
color 0B
echo.
echo               ✓✓✓ COMPILACION EXITOSA ✓✓✓
echo.
echo ════════════════════════════════════════════════════════════════════
echo.
echo La app se compiló correctamente.
echo.
echo APK generado en:
echo app\build\outputs\apk\debug\app-debug.apk
echo.
echo PROXIMOS PASOS:
echo   1. Abre Android Studio
echo   2. File > Sync Project with Gradle Files
echo   3. Build > Rebuild Project
echo   4. Run > Run 'app'
echo.
echo ════════════════════════════════════════════════════════════════════
echo.
pause

