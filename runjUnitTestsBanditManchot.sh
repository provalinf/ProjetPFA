#!/usr/bin/env bash

mkdir bin

cd Monnaie

# compile
javac -d ../bin -sourcepath src -cp .:test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar test/company/*.java test/company/Devise/*.java

#Windows
#javac -d bin -sourcepath src -cp .;test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar test/company/*.java test/company/Devise/*.java

cd ..

#run
java -jar test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c company.Devise.EuroTest
java -jar test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c company.Devise.FrancTest
java -jar test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c company.PieceQuantiteTest
java -jar test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c company.MachineConversionTest
