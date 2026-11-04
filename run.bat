@echo off
if not exist out mkdir out
javac -d out src\shopease\*.java && java -cp out shopease.Main
