@echo off
cd src
set "base_directory=%~dp0"
java "-Djava.library.path=%base_directory%\src" -classpath "%base_directory%\target\classes;%base_directory%\src" aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher