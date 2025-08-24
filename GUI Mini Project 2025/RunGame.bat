@echo off
set PROJECT_DIR=C:\Users\iznamu\OneDrive - Panyapiwat Institute of Management\CAI 2.1 2025\OOP 2.1 2025\GUI Mini Project 2025

cd /d "%PROJECT_DIR%"

REM สร้างโฟลเดอร์ bin ถ้ายังไม่มี
if not exist bin mkdir bin

echo Compiling Java files...
javac -encoding UTF-8 -d bin Game\UI\*.java Game\Logic\*.java Game\Main\*.java

if errorlevel 1 (
    echo [ERROR] Compilation failed.
    pause
    exit /b
)

echo Running Game...
java -cp bin Game.Main.RunGame

pause
