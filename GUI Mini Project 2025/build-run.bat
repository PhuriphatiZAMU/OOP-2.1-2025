@echo off
echo 🔨 Compiling Java source with UTF-8 ...
javac -encoding UTF-8 -d . FootballerImage\FootballerPuzzle.java

if errorlevel 1 (
    echo ❌ Compile failed
    pause
    exit /b
)

echo 📝 Creating manifest ...
echo Main-Class: FootballerImage.FootballerPuzzle>manifest.txt
echo.>>manifest.txt

echo 📦 Building JAR with images ...
jar cfm FootballerPuzzle.jar manifest.txt FootballerImage\*.class FootballerImage\*.jpg

echo 🚀 Running application ...
start javaw -jar FootballerPuzzle.jar