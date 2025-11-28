@echo off
echo ============================================
echo    COMPILANDO BARBERSHOP APP CON LOGIN
echo ============================================
echo.

echo [1/4] Deteniendo demonios de Gradle...
call gradlew --stop
echo.

echo [2/4] Limpiando proyecto...
call gradlew clean
echo.

echo [3/4] Compilando aplicacion...
call gradlew assembleDebug
echo.

echo [4/4] Verificando compilacion...
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo.
    echo ============================================
    echo    COMPILACION EXITOSA!
    echo ============================================
    echo.
    echo APK generado en:
    echo app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo Ahora puedes:
    echo 1. Instalar el APK en tu dispositivo
    echo 2. Ejecutar desde Android Studio
    echo.
    echo Credenciales de prueba:
    echo Email: demo@barbershop.com
    echo Password: 123456
    echo.
) else (
    echo.
    echo ============================================
    echo    ERROR EN LA COMPILACION
    echo ============================================
    echo.
    echo Por favor:
    echo 1. Abre el proyecto en Android Studio
    echo 2. Sync Project with Gradle Files
    echo 3. Build - Rebuild Project
    echo.
)

pause

