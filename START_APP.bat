@echo off
title BarberShop App - Ejecutor
echo =============================================
echo     BARBERSHOP APP - LAUNCHER
echo =============================================
echo.

echo Compilando aplicacion...
call gradlew clean assembleDebug

if errorlevel 1 (
    echo.
    echo ERROR: Fallo en la compilacion
    echo Revisa el codigo para errores
    pause
    exit /b 1
)

echo.
echo Compilacion exitosa!
echo.
echo =============================================
echo   INSTRUCCIONES PARA EJECUTAR LA APP:
echo =============================================
echo.
echo 1. Abre Android Studio
echo 2. Ve a Tools ^> AVD Manager
echo 3. Inicia un emulador Android
echo 4. O conecta tu dispositivo Android via USB
echo.
echo Luego ejecuta: gradlew installDebug
echo.
echo CREDENCIALES DE LOGIN:
echo Email: axxelduran342@gmail.com
echo Password: axelduran8
echo.
echo Token Xano disponible:
echo eyJhbGciOiJBMjU2S1ciLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiemlwIjoiREVGIn0...
     echo.
     pause
