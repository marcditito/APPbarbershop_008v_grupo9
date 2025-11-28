@echo off
echo === CLEAN COMPLETO DE GRADLE ===

echo Matando procesos Java...
taskkill /F /IM java.exe 2>nul
taskkill /F /IM javaw.exe 2>nul

echo Eliminando cache global de Gradle...
if exist "%USERPROFILE%\.gradle" rmdir /s /q "%USERPROFILE%\.gradle"

echo Eliminando archivos locales del proyecto...
if exist ".gradle" rmdir /s /q ".gradle"
if exist "build" rmdir /s /q "build"
if exist "app\build" rmdir /s /q "app\build"

echo Esperando...
timeout /t 3 /nobreak >nul

echo Iniciando compilacion con Gradle limpio...
gradlew.bat assembleDebug --no-daemon --refresh-dependencies --recompile-scripts

echo === PROCESO COMPLETADO ===
pause
