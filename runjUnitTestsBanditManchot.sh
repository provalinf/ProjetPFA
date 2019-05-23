#!/usr/bin/env bash

mkdir bin

cd Monnaie

# compile
javac -d ../bin -sourcepath src -cp .:test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar test/BanditManchot/*.java test/BanditManchot/Devise/*.java

#Windows
#javac -d ../bin -sourcepath src -cp .;test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar test/BanditManchot/*.java test/BanditManchot/Devise/*.java

cd ..

#run
java -jar Monnaie/test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c BanditManchot.Devise.EuroTest
java -jar Monnaie/test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c BanditManchot.Devise.FrancTest
java -jar Monnaie/test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c BanditManchot.PieceQuantiteTest
java -jar Monnaie/test/jUnit5.3.1/junit-platform-console-standalone-1.1.0.jar --cp bin/ -c BanditManchot.MachineConversionTest
