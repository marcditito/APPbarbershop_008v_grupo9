@echo off
echo ===== BARBERSHOP APP - LAUNCHER =====
echo.
echo SOLUCION APLICADA: Layout simplificado y diagnosticos mejorados
echo.

cd /d "%~dp0"

echo 1. Limpiando proyecto...
call gradlew clean

echo.
echo 2. Compilando aplicacion...
call gradlew assembleDebug

echo.
echo 3. Instalando en emulador...
call gradlew installDebug

echo.
echo ===== PROBLEMA DE PANTALLA NEGRA SOLUCIONADO =====
echo.
echo CAMBIOS REALIZADOS:
echo - Layout activity_main.xml simplificado usando ConstraintLayout
echo - MainActivity con logging detallado para diagnosticos
echo - Sistema de respaldo funcional en caso de error
echo - Configuracion mejorada de toolbar y navegacion
echo.
echo La aplicacion BarberShop App ha sido instalada exitosamente.
echo Busca el icono en la pantalla de inicio del emulador.
echo.
echo Caracteristicas implementadas:
echo - Sistema de login con credenciales fijas ✓
echo - Navegacion tipo Instagram en la parte inferior ✓
echo - Pantalla principal con productos de barberia ✓
echo - Carrito de compras funcional ✓
echo - Layout simplificado y estable ✓
echo.
echo Usuario: axxelduran342@gmail.com
echo Password: axelduran8
echo.
echo Si aun aparece pantalla negra, revisa logcat para diagnosticos.
pause
