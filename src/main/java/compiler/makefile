make: compiler.jflex compiler.y

runFlex:
	jflex  compiler.jflex

runBison:
	bison compiler.y --file-prefix=CompilerBison --print-datadir --defines=testInterface

compileAll:	runFlex runBison

cleanBison:
	rm CompilerBison.java

cleanFlex:
	rm CompilerFlex.java

cleanAll:
	rm CompilerFlex.java CompilerBison.java