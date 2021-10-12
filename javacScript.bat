@echo off

set CD=%~dp0
set javafxHome=%CD%lib\javafx-sdk-17.0.0.1\lib
set mainPath=%CD%src\ru\vsu\cs\raspopov
set logPath=%CD%lib\logback

set utils=-d bin %mainPath%\utils\*.java
set pieces=-d bin %mainPath%\chess\essence\pieces\*.java
set board=-d bin %mainPath%\chess\essence\board\*.java
set graphics=-d bin %mainPath%\chess\graphics\*.java
set gameservice=-d bin %mainPath%\chess\gameservice\*.java
set chess=-d bin %mainPath%\chess\*.java

javac --module-path "%javafxHome%" --upgrade-module-path "%logPath%" --add-modules ALL-MODULE-PATH %utils% %pieces% %board% %graphics% %gameservice% %chess%