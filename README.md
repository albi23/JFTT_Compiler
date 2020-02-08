Projekt kompilatora Albert Piekielny

Główna klasa Compiler znajduje się w katalogu src/main/java/, powstaje w momencie uruchomienia polecenia

`make make`
Katalog virtualmachine zawiera maszynę virtualną na której można uruchamiać przekompilowane programy 

Wymagane jest pobranie `JFlex 1.7.0`( odnośnik do instalacji : https://www.jflex.de/download.html lub `sudo apt-get install -y jflex` ), oraz użycie Java 8
parser (bison.y) kompilowany jest przy użyciu wersji bison'a ` (GNU Bison) 3.3.2` (https://ftp.gnu.org/gnu/bison/ )


flex.jflex zawiera informacje na temat regexów zamieszczonych przez programistę, które następnie po 
wygenerowaniu (za pomocą polexenia `make make `) pliku Flex.java są przekazowane jako tokeny do parsera, którego definiuje plik bison.y  

bison.y generuje interfejs Lexer, który jest implementowany w klasie Flex, generowanej przez flex.flex

pom.xml wymusza używanie Java8, oraz w przypadku pakowania archwium jar główną klasę podczas uruchaminia jar'a

sposób użycia :

`java Compiler <inputfile>  <outputfile>`
lub 
`java -jar  Compiler.jar`

po wygenerowaniu archiwum *.jar poleceniem `make createJavaJar` bądź też przy użyciu maven'a `mvn package`
