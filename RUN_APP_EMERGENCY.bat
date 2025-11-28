@echo off
echo ========================================
echo  🏪 BARBERSHOP APP - LAUNCH ULTRA SAFE
echo ========================================
echo.

echo 📱 1. Compilando aplicacion...
call gradlew clean
if errorlevel 1 goto :error

call gradlew assembleDebug
if errorlevel 1 goto :error

echo 📱 2. Instalando en emulador...
call gradlew installDebug
if errorlevel 1 goto :error

echo 🚀 3. Iniciando aplicacion...
echo.
echo ✅ APLICACION INSTALADA EXITOSAMENTE
echo.
echo 🔧 VERSION ULTRA-ROBUSTA ACTIVADA
echo    - MainActivity simplificada
echo    - Sin dependencias complejas
echo    - UI creada programaticamente
echo    - Sistema de respaldo incluido
echo.
echo 📧 CREDENCIALES DE LOGIN:
echo    Email: axxelduran342@gmail.com
echo    Password: axelduran8
echo.
echo 🎯 FUNCIONES DISPONIBLES:
echo    ✅ Login funcional
echo    ✅ Interfaz de barberia
echo    ✅ Lista de productos
echo    ✅ Navegacion tipo Instagram
echo    ✅ Carrito de compras basico
echo    ✅ Sistema anti-crash
echo.

REM Intentar abrir la app automaticamente
set ADB_PATH=%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe
if exist "%ADB_PATH%" (
    echo 🚀 Abriendo aplicacion automaticamente...
    "%ADB_PATH%" shell am start -n com.example.barbershopapp/.ui.LoginActivity >nul 2>&1
    if errorlevel 0 (
        echo ✅ App iniciada automaticamente
    ) else (
        echo ⚠️ Abrir manualmente desde el emulador
    )
) else (
    echo ⚠️ ADB no encontrado, abrir manualmente
)

echo.
echo 🎉 PROCESO COMPLETADO
echo.
echo 📝 INSTRUCCIONES:
echo    1. Buscar "BarberShopApp" en el emulador
echo    2. Hacer login con las credenciales de arriba
echo    3. Disfrutar la interfaz funcional
echo.
echo 🔧 SOLUCION IMPLEMENTADA:
echo    - MainActivity completamente reescrita
echo    - UI programatica ultra-estable
echo    - Sistema de respaldo multicapa
echo    - Eliminadas dependencias problematicas
echo.
pause
goto :end

:error
echo.
echo ❌ ERROR EN LA COMPILACION
echo.
echo 🔧 SOLUCIONES:
echo    1. Verificar que Android Studio este cerrado
echo    2. Ejecutar LIMPIAR_TODO.bat
echo    3. Reiniciar el emulador
echo    4. Intentar nuevamente
echo.
pause

:end
