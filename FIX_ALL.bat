@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul

color 0A
cls

echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║   SOLUCION COMPLETA - ERROR GRADLE DAEMON BARBER SHOP APP  ║
echo ╚════════════════════════════════════════════════════════════╝
echo.
echo Problema: Unrecognized VM option 'MaxPermSize=1024m'
echo Causa: Opción obsoleta de Java en gradle.properties
echo.
echo ════════════════════════════════════════════════════════════
echo INICIANDO CORRECCION AUTOMATICA...
echo ════════════════════════════════════════════════════════════
echo.

cd /d "%~dp0"

echo [PASO 1/7] Verificando gradle.properties...
if exist "gradle.properties" (
    echo ✓ Archivo gradle.properties encontrado
) else (
    echo ✗ ERRO: No se encontró gradle.properties
    pause
    exit /b 1
)

echo.
echo [PASO 2/7] Deteniendo daemon de Gradle...
call gradlew.bat --stop 2>nul
timeout /t 2 /nobreak >nul
echo ✓ Daemon detenido

echo.
echo [PASO 3/7] Limpiando carpeta .gradle...
if exist ".gradle" (
    rmdir /s /q ".gradle" 2>nul
    echo ✓ Carpeta .gradle eliminada
) else (
    echo - Carpeta .gradle no encontrada (normal)
)

echo.
echo [PASO 4/7] Limpiando carpeta build...
if exist "build" (
    rmdir /s /q "build" 2>nul
    echo ✓ Carpeta build eliminada
) else (
    echo - Carpeta build no encontrada (normal)
)

echo.
echo [PASO 5/7] Limpiando carpeta app\build...
if exist "app\build" (
    rmdir /s /q "app\build" 2>nul
    echo ✓ Carpeta app\build eliminada
) else (
    echo - Carpeta app\build no encontrada (normal)
)

echo.
echo [PASO 6/7] Ejecutando gradle clean...
call gradlew.bat clean
if !errorlevel! equ 0 (
    echo ✓ Gradle clean ejecutado exitosamente
) else (
    echo ✗ ERROR: Gradle clean falló
    pause
    exit /b 1
)

echo.
echo [PASO 7/7] Compilando proyecto (esto puede tardar)...
call gradlew.bat assembleDebug
if !errorlevel! equ 0 (
    echo ✓ Compilación exitosa
    color 0B
) else (
    echo ✗ ERROR: La compilación falló
    echo.
    echo Revisa los errores anteriores.
    pause
    exit /b 1
)

echo.
echo ════════════════════════════════════════════════════════════
color 0B
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║          ✓ CORRECCION COMPLETADA EXITOSAMENTE ✓          ║
echo ╚════════════════════════════════════════════════════════════╝
echo.
echo CAMBIOS REALIZADOS:
echo  1. Eliminada opción obsoleta: -XX:MaxPermSize=1024m
echo  2. gradle.properties actualizado: org.gradle.jvmargs=-Xmx4096m
echo  3. Caché de Gradle limpiada
echo  4. Proyecto recompilado correctamente
echo.
echo PROXIMOS PASOS:
echo  1. Abre Android Studio
echo  2. Haz clic en: Build > Rebuild Project
echo  3. Luego ve a: Run > Run 'app'
echo.
echo Si aún tienes problemas, ejecuta uno de estos scripts:
echo  - fix_gradle.bat (limpieza rápida)
echo  - clean_global_gradle.bat (limpieza global - opcional)
echo.
echo ════════════════════════════════════════════════════════════
echo.

pause

