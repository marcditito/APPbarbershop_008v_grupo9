@echo off
echo ============================================
echo Limpieza completa del proyecto Gradle
echo ============================================
echo.

cd /d "%~dp0"

REM Detener el daemon de Gradle
echo 1. Deteniendo daemon de Gradle...
call gradlew.bat --stop
echo.

REM Eliminar carpetas de build
echo 2. Eliminando carpetas de build...
if exist ".gradle" (
    echo   - Eliminando .gradle
    rmdir /s /q ".gradle"
)
if exist "build" (
    echo   - Eliminando build
    rmdir /s /q "build"
)
if exist "app\build" (
    echo   - Eliminando app\build
    rmdir /s /q "app\build"
)

REM Limpiar caché local de gradle
echo.
echo 3. Ejecutando gradle clean...
call gradlew.bat clean

echo.
echo ============================================
echo Limpieza completada exitosamente
echo ============================================
echo.
echo El proyecto está listo para reconstruir.
echo Próximo paso: ejecutar 'gradlew.bat assembleDebug'
echo.
pause



