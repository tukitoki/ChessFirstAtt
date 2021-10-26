
$javafxHome="$CD\Chess\lib\javafx-sdk-17.0.0.1\lib"
$mainPath="$CD/src/ru/vsu/cs/raspopov"
$logPath="$CD\lib\logback"

javac --module-path "$javafxHome" --upgrade-module-path "$logPath" --add-modules ALL-MODULE-PATH -d bin $mainPath/utils/*.java -d bin $mainPath/chess/pieces/*.java -d bin $mainPath/chess/essence/*.java -d bin $mainPath/chess/board/*.java -d bin $mainPath/chess/*.java