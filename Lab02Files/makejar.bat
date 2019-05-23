@echo off
cls

set path="C:\Program Files\Java\jdk-10.0.2\bin";c:\Windows
set classpath="C:\Program Files\Java\jdk-10.0.2\bin";.;matrix.jar

javac -cp %classpath% Equations.java
java -cp %classpath% Equations