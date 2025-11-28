@echo off
echo ========================================
echo   LIMPIEZA Y RECONSTRUCCION COMPLETA
echo   Kotlin 2.0.21 + KAPT + JDK 17
echo ========================================
echo.

REM Detener daemon de Gradle
echo [1/6] Deteniendo procesos de Gradle...
call gradlew --stop 2>nul

REM Eliminar directorios de compilacion
echo [2/6] Eliminando directorios de compilacion...
if exist "app\build" rmdir /s /q "app\build"
if exist "build" rmdir /s /q "build"
if exist ".gradle" rmdir /s /q ".gradle"
if exist ".idea\caches" rmdir /s /q ".idea\caches"

REM Limpiar caches de Gradle (opcional, comentado por defecto)
REM echo [3/6] Limpiando caches globales de Gradle...
REM if exist "%USERPROFILE%\.gradle\caches" rmdir /s /q "%USERPROFILE%\.gradle\caches"

echo [3/6] Ejecutando Gradle Clean...
call gradlew clean --no-daemon

echo [4/6] Sincronizando dependencias...
call gradlew --refresh-dependencies --no-daemon

echo [5/6] Compilando proyecto...
call gradlew build --no-daemon

echo [6/6] Proceso completado!
echo.
echo ========================================
echo   RESULTADO:
echo ========================================
if %ERRORLEVEL% EQU 0 (
    echo   ✓ EXITO: El proyecto se compilo correctamente
    echo.
    echo   Puedes abrir Android Studio y:
    echo   1. File ^> Invalidate Caches / Restart
    echo   2. Build ^> Rebuild Project
) else (
    echo   X ERROR: Hubo problemas en la compilacion
    echo.
    echo   Revisa el archivo SOLUCION_KOTLIN_KAPT.md
    echo   para mas informacion sobre solucion de problemas
)
echo ========================================
echo.
pause

