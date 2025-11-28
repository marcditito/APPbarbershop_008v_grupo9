@echo off
echo =================================================
echo     BARBERSHOP APP - EJECUTOR AUTOMATICO
echo =================================================
echo.

echo 1. Compilando la aplicacion...
call gradlew assembleDebug
if errorlevel 1 (
    echo ERROR: La compilacion fallo
    pause
    exit /b 1
)

echo.
echo 2. Verificando emulador o dispositivo...
"%ANDROID_HOME%\platform-tools\adb.exe" devices

echo.
echo 3. Instalando aplicacion...
call gradlew installDebug
if errorlevel 1 (
    echo.
    echo ========================================
    echo   NO HAY EMULADOR/DISPOSITIVO CONECTADO
    echo ========================================
    echo.
    echo Para ejecutar la aplicacion:
    echo 1. Abrir Android Studio
    echo 2. Iniciar un emulador (AVD Manager)
    echo 3. Ejecutar este script de nuevo
    echo.
    echo O conectar un dispositivo Android via USB
    echo con depuracion USB habilitada
    echo.
    pause
    exit /b 1
)

echo.
echo ========================================
echo   APLICACION INSTALADA EXITOSAMENTE
echo ========================================
echo.
echo Iniciando aplicacion...
"%ANDROID_HOME%\platform-tools\adb.exe" shell am start -n com.example.barbershopapp/.ui.LoginActivity

echo.
echo La aplicacion se ha ejecutado en tu dispositivo/emulador
echo.
pause
