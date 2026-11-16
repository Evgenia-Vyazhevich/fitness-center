@echo off
setlocal
if defined MAVEN_HOME if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    call "%MAVEN_HOME%\bin\mvn.cmd" %*
    exit /b
)
set "FITNESS_MAVEN=%APPDATA%\Code\User\globalStorage\pleiades.java-extension-pack-jdk\maven\latest\bin\mvn.cmd"
if exist "%FITNESS_MAVEN%" (
    call "%FITNESS_MAVEN%" %*
    exit /b
)
echo Maven was not found. Set MAVEN_HOME to the Maven installation directory. 1>&2
exit /b 1
