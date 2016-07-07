@echo off
cd /d %~dp0
cd bin
java -classpath ./:jcommon-1.0.23.jar:jfreechart-1.0.19.jar ShowView
