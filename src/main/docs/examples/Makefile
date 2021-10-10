
.PHONY: * 


first:
	export CLASSPATH=./ssj-3.3.1.jar:./colt-1.2.0.jar:./commons-math3-3.6.1.jar:./concurrent-1.3.4.jar:./jcommon-1.0.15.jar:./jfreechart-1.0.12.jar:./optimization-1.3.jar:./py4j0.10.9.2.jar

second: first
	javac markovchainrqmc/SobolEntryPoint.java
	java markovchainrqmc.SobolEntryPoint
