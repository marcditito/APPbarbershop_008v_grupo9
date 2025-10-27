@echo off
setlocal enabledelayedexpansion

color 0A
cls

echo.
echo ════════════════════════════════════════════════════════════
echo          VERIFICACION DE CONFIGURACION GRADLE
echo ════════════════════════════════════════════════════════════
echo.

echo [1] Verificando Java...
java -version 2>&1 | find /i "version" >nul
if !errorlevel! equ 0 (
    echo ✓ Java instalado correctamente
    echo.
    java -version 2>&1
) else (
    echo ✗ ERRO: Java no encontrado
    pause
    exit /b 1
)

echo.
echo [2] Verificando gradle.properties...
cd /d "%~dp0"

findstr /i "MaxPermSize" gradle.properties >nul
if !errorlevel! equ 0 (
    echo ✗ ALERTA: Aún existe MaxPermSize en gradle.properties
    echo.
    findstr /i "MaxPermSize" gradle.properties
    pause
    exit /b 1
) else (
    echo ✓ gradle.properties limpio (sin MaxPermSize)
)

echo.
echo [3] Mostrando configuración de JVM...
findstr "org.gradle.jvmargs" gradle.properties
if !errorlevel! equ 0 (
    echo ✓ Configuración correcta
) else (
    echo ✗ No se encontró org.gradle.jvmargs
)

echo.
echo [4] Verificando versión de Gradle...
call gradlew.bat --version 2>&1 | find "Gradle"

echo.
echo ════════════════════════════════════════════════════════════
echo          VERIFICACION COMPLETADA
echo ════════════════════════════════════════════════════════════
echo.
echo Si todos los checks son ✓, el problema está resuelto.
echo.
echo Próximo paso: Ejecuta "FIX_ALL.bat" para compilar.
echo.

pause

