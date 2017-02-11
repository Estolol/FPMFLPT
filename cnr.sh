#!/bin/sh

if [ "$#" -ne 1 ] || ! [ -f "input/$1" ]; then
  echo "Usage: $0 <inputname>" >&2
  exit 1
fi

JNA_PATH="/usr/share/jna/jna.jar"
PARALLEL_PATH="/usr/share/parallelcolt/parallelcolt-0.9.4.jar"
JOM_PATH="/usr/share/jom/jom-0.2.0.1.jar"
AMPLAPI_PATH="/usr/share/ampl/amplapi/lib/ampl-1.2.2.jar"

CLASSPATH="bin:$JOM_PATH:$JNA_PATH:$PARALLEL_PATH:$AMPLAPI_PATH"
#GLPK_PATH="/usr/share/java/glpk-java.jar"
#sed -i 's:String input =:String input = args[0];//:g' src/main.java
javac -cp $CLASSPATH -d bin src/*.java
java -cp $CLASSPATH main  < "input/"$1
