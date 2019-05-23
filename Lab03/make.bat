@echo off
cls

set PATH="c:\Program Files\Java\jdk-10.0.2\bin";"c:\windows\system32"
set CLASSPATH="c:\Program Files\Java\jre7";.

javac -cp %CLASSPATH% *.java
java -cp %CLASSPATH% MazeDriver
