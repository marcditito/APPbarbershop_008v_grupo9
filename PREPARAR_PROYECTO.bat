@echo off
cls
echo ============================================
echo    PREPARANDO PROYECTO PARA ANDROID STUDIO
echo ============================================
echo.

echo [1/5] Deteniendo procesos de Gradle...
call gradlew --stop
timeout /t 2 /nobreak >nul
echo Completado!
echo.

echo [2/5] Limpiando archivos temporales...
if exist ".gradle\" rmdir /s /q ".gradle"
if exist "app\build\" rmdir /s /q "app\build"
if exist "build\" rmdir /s /q "build"
echo Completado!
echo.

echo [3/5] Limpiando proyecto Gradle...
call gradlew clean
echo Completado!
echo.

echo [4/5] Compilando proyecto...
call gradlew assembleDebug --no-daemon
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ============================================
    echo    ERROR EN COMPILACION
    echo ============================================
    echo.
    echo Por favor:
    echo 1. Abre Android Studio
    echo 2. File -^> Sync Project with Gradle Files
    echo 3. Espera a que termine
    echo 4. Click en el boton Run (triangulo verde)
    echo.
    pause
    exit /b 1
)
echo Completado!
echo.

echo [5/5] Verificando APK...
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo.
    echo ============================================
    echo    PROYECTO LISTO!
    echo ============================================
    echo.
    echo APK compilado exitosamente en:
    echo app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo SIGUIENTES PASOS:
    echo 1. Abre Android Studio
    echo 2. File -^> Open -^> Selecciona esta carpeta
    echo 3. Espera a que cargue el proyecto (1-2 min)
    echo 4. Click en el boton Run (triangulo verde)
    echo.
    echo CREDENCIALES DE PRUEBA:
    echo Email: demo@barbershop.com
    echo Password: 123456
    echo.
) else (
    echo.
    echo No se encontro el APK generado.
    echo Usa Android Studio para compilar.
    echo.
)

echo ============================================
pause

