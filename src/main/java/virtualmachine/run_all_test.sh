#!/bin/bash

for file in ./* ; do
  file_extension=${file##*\.}
  machine_type=maszyna-wirtualna;

  if [ $1 = 'cln' ]; then
      machine_type=maszyna-maszyna-wirtualna-cln
  fi

  if [ $file_extension = 'txt' ] ; then
      echo -e "\e[34m \n\n======== Runned file ======= \e[39m"
      echo -e "\e[34m  $file           \e[39m"
      ./$machine_type $file
  fi

done
