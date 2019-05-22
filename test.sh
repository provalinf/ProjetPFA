#!/usr/bin/env bash

cd JUnit5DynamicConsole

# compile
javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.1.0.jar src/EuroTest.java
javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.1.0.jar src/FrancTest.java
javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.1.0.jar src/PieceQuantiteTest.java
javac -d bin -sourcepath src -cp .:lib/junit-platform-console-standalone-1.1.0.jar src/MachineConversionTest.java


#run
java -jar lib/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c EuroTest
java -jar lib/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c FrancTest
java -jar lib/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c PieceQuantiteTest
java -jar lib/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c MachineConversionTest
