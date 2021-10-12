$javafxHome="C:\filesjava\secondCourse\Chess\lib\javafx-sdk-17.0.0.1\lib"
$logPath="C:\filesjava\secondCourse\Chess\lib\logback"

cd C:\filesJava\secondCourse\Chess
java --module-path "$javafxHome" --upgrade-module-path "$logPath" --add-modules ALL-MODULE-PATH -jar Chess.jar