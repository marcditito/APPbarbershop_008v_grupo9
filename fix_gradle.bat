@echo off
setlocal enabledelayedexpansion

echo.
echo ============================================
echo CORRECCION DE ERROR GRADLE - BARBER SHOP APP
echo ============================================
echo.
echo Esta secuencia:
echo 1. Detiene el daemon de Gradle
echo 2. Elimina caché y carpetas de build
echo 3. Ejecuta gradle clean
echo 4. Reconstruye el proyecto
echo.

cd /d "%~dp0"

echo [1/5] Deteniendo daemon de Gradle...
call gradlew.bat --stop 2>nul
timeout /t 2 /nobreak

echo [2/5] Eliminando carpeta .gradle...
if exist ".gradle" rmdir /s /q ".gradle" 2>nul
echo Completado.

echo [3/5] Eliminando carpeta build...
if exist "build" rmdir /s /q "build" 2>nul
echo Completado.

echo [4/5] Eliminando carpeta app\build...
if exist "app\build" rmdir /s /q "app\build" 2>nul
echo Completado.

echo [5/5] Ejecutando gradle clean...
call gradlew.bat clean

echo.
echo ============================================
echo LIMPIEZA COMPLETADA EXITOSAMENTE
echo ============================================
echo.
echo PROXIMOS PASOS:
echo 1. Abre Android Studio
echo 2. Ve a Build > Rebuild Project
echo 3. Ejecuta la app con Run > Run 'app'
echo.
echo Si persisten los errores, ejecuta:
echo   gradlew.bat assembleDebug
echo.
pause

