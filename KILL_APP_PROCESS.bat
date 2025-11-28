@echo off
echo ========================================
echo    BARBERSHOP APP - FULL RESTART
echo ========================================
set APP_ID=com.example.barbershopapp
set ACTIVITY=com.example.barbershopapp/.ui.MainActivity

echo Verificando adb...
where adb >nul 2>nul
if errorlevel 1 (
  echo ADB no encontrado. Verifica que Android SDK este instalado y en PATH.
  echo Intentando buscar ADB en ubicaciones comunes...

  if exist "%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe" (
    set PATH=%LOCALAPPDATA%\Android\Sdk\platform-tools;%PATH%
    echo ADB encontrado en Android SDK local
  ) else (
    echo ADB no encontrado. Abre Android Studio y verifica SDK.
    pause
    exit /b 1
  )
)

echo Verificando dispositivos conectados...
adb devices

echo Intentando detener procesos de la aplicacion...
taskkill /F /IM "com.example.barbershopapp" 2>nul
taskkill /F /IM qemu-system* 2>nul

echo Forzando detener app en dispositivo/emulador...
adb shell am force-stop %APP_ID% 2>nul

echo Desinstalando version anterior...
adb uninstall %APP_ID% 2>nul

echo.
echo Limpiando gradle...
call gradlew clean

echo.
echo Recompilando aplicacion...
call gradlew assembleDebug

if not exist app\build\outputs\apk\debug\app-debug.apk (
  echo ERROR: APK no generada en app\build\outputs\apk\debug\app-debug.apk
  pause
  exit /b 1
)

echo.
echo Instalando APK en dispositivo/emulador...
adb install -r app\build\outputs\apk\debug\app-debug.apk

if errorlevel 1 (
  echo Error al instalar APK. Reiniciando ADB...
  adb kill-server
  timeout /t 2 >nul
  adb start-server
  adb install -r app\build\outputs\apk\debug\app-debug.apk
)

echo.
echo Iniciando aplicacion...
adb shell am start -n %ACTIVITY% || (
  echo No se pudo iniciar. Intentando con LoginActivity...
  adb shell am start -n %APP_ID%/.ui.LoginActivity
)

echo.
echo ========================================
echo   PROCESO COMPLETADO
echo ========================================
echo La aplicacion deberia estar ejecutandose ahora.
echo Si hay errores, revisa el Logcat en Android Studio.
echo.
pause
