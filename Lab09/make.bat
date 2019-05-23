@echo off
cls

javac -cp .;queue.jar *.java
java -ea -cp .;queue.jar BSTTest
