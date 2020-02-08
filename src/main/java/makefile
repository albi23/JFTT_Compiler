make: flex.jflex bison.y

runFlex:
	jflex  flex.jflex

runBison:
	bison bison.y --file-prefix=Compiler

compileJava:
	  javac Compiler.java

make:	runFlex runBison compileJava

cleanBison:
	rm Compiler.java

cleanFlex:
	rm Flex.java

cleanAll:
	rm Flex.java Compiler.java *.class *.mf *.jar

createJavaJar:
	make
	jar cvfe Compiler.jar Compiler *.class
	echo -e "\e[34mCreated : Compiler.jar\e[39m"
