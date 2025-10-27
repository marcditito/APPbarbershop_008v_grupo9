@echo off
REM Script para limpiar caché global de Gradle (opcional)
REM Ejecutar como administrador si es necesario

echo.
echo ============================================
echo Limpieza GLOBAL de Gradle (Opcional)
echo ============================================
echo.
echo Este script elimina la caché GLOBAL de Gradle
echo Ubicacion: C:\Users\%username%\.gradle
echo.
echo ADVERTENCIA: Esto forzará la descarga de todas
echo las dependencias nuevamente en el siguiente build.
echo.

set /p confirm="¿Deseas continuar? (S/N): "
if /i not "%confirm%"=="S" (
    echo Operacion cancelada.
    pause
    exit /b
)

echo.
echo Eliminando caché global de Gradle...
rmdir /s /q "%USERPROFILE%\.gradle" 2>nul
if exist "%USERPROFILE%\.gradle" (
    echo No se pudo eliminar. Intenta ejecutar como administrador.
) else (
    echo ✓ Caché global eliminada correctamente.
)

echo.
echo ============================================
echo Limpieza global completada
echo ============================================
echo.
pause

