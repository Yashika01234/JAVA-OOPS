#!/bin/sh
mkdir -p out && javac -d out src/shopease/*.java && java -cp out shopease.Main
