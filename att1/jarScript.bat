@echo off

set CD=%~dp0
set mainPath=ru\vsu\cs\raspopov
set binPath=%CD%bin

set utils=%mainPath%\utils\*.class
set pieces=%mainPath%\chess\essence\pieces\*.class
set board=%mainPath%\chess\essence\board\*.class
set graphics=%mainPath%\chess\graphics\*.class
set gameservice=%mainPath%\chess\gameservice\*.class
set chess=%mainPath%\chess\*.class

cd %binPath%
jar cvfe ../Chess.jar ru.vsu.cs.raspopov.chess.Game %utils% %pieces% %board% %graphics% %gameservice% %chess%