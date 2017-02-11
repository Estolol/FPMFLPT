#!/bin/sh

if [ "$#" -ne 1 ] || ! [ -f "input/$1.txt" ]; then
  echo "Usage: $0 <inputname>" >&2
  exit 1
fi

javac -d bin src/*.java
java -cp bin main $1
