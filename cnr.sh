#!/bin/sh

if [ "$#" -ne 1 ] || ! [ -f "input/$1.txt" ]; then
  echo "Usage: $0 <inputname>" >&2
  exit 1
fi

JNA_PATH="/usr/share/JNA/jna.jar"
PARALLEL_PATH="/usr/share/parallel-colt/parallelcolt.jar"
JOM_PATH="/usr/share/JOM/jom.jar"
#GLPK_PATH="/usr/share/java/glpk-java.jar"
#sed -i 's:String input =:String input = args[0];//:g' src/main.java
javac -cp "$JOM_PATH:$JNA_PATH:$PARALLEL_PATH" -d bin src/*.java
java -cp "bin:$JOM_PATH:$JNA_PATH:$PARALLEL_PATH" main $1
