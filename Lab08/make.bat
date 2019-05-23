@echo off
cls

javac -cp .;queue.jar *.java
java -cp .;queue.jar AdaptableTableTest
