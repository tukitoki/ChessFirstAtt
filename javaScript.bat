@echo off

set CD=%~dp0
set javafxHome=%CD%lib\javafx-sdk-17.0.0.1\lib
set mainPath=%CD%src\ru\vsu\cs\raspopov
set logPath=%CD%lib\logback


java --module-path "%javafxHome%" --upgrade-module-path "%logPath%" --add-modules ALL-MODULE-PATH -jar %CD%Chess.jar