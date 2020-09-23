@echo off
rem Generate Eclipse classpath based on Gradle dependencies

gradle androidEclipse cleanEclipseClasspath eclipse
pause
