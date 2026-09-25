# Copy this file to set-env.ps1 and edit the JAVA_HOME path
# to match your machine. Then run: .\set-env.ps1
#
# This script sets the JDK for the current PowerShell session only.
# Close the terminal and the setting goes away.

$env:JAVA_HOME = "C:\path\to\your\jdk-25"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

Write-Host "JAVA_HOME = $env:JAVA_HOME"
java -version
mvn -version