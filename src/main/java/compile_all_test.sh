#!/bin/bash

# === Skrypt kompiluje wszystkie testy w podfolderze implv2                     ===
# === następnie zapisuje je w folderze virtual machine gdzie można je uruchomić ===
# === wpierw należy przekompilować projekt poleceniem make make                 ===
for file in tests/implv2/*; do
  java Compiler $file ./virtualmachine/${file##*/}-result.txt
  echo -e "\e[34m Compiled file : ${file##*/} \e[39m"
done

