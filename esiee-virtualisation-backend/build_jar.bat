CD %0\..
D:
SET current_folder=%CD%

REM ---- Mettre le chemin d'un java supérieur à 11 ----
SET JAVA_HOME=%current_folder%\jdk-17.0.1
apache-maven-3.8.5\bin\mvn clean package
pause