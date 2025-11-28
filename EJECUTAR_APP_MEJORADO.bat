@echo off
echo ==========================================
echo        EJECUTAR BARBERSHOP APP
echo ==========================================

cd /d "%~dp0"

echo.
echo Verificando dispositivos conectados...
call adb devices

echo.
echo Compilando aplicacion...
call gradlew.bat assembleDebug

if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Falló la compilación
    pause
    exit /b 1
)

echo.
echo Instalando aplicación...
call gradlew.bat installDebug

if %ERRORLEVEL% NEQ 0 (
    echo ADVERTENCIA: No se pudo instalar automáticamente
    echo Asegurate de tener un emulador ejecutándose
    echo.
    echo Para ejecutar manualmente:
    echo 1. Abre Android Studio
    echo 2. Inicia un emulador Android
    echo 3. Ejecuta este script nuevamente
    echo.
    pause
    exit /b 1
)

echo.
echo Iniciando aplicación...
call adb shell am start -n com.example.barbershopapp.debug/com.example.barbershopapp.ui.LoginActivity

echo.
echo ==========================================
echo   APP EJECUTADA CORRECTAMENTE
echo ==========================================
echo.
echo La aplicación BarberShop debería estar ejecutándose
echo en el emulador Android.
echo.
echo CREDENCIALES DE LOGIN:
echo Email: axxelduran342@gmail.com
echo Password: axelduran8
echo.
echo ==========================================
pause
