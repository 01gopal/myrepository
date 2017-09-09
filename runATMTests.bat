

title Project ATM

cd %~dp0

call mvn clean test

echo "*********Test Coverage report is ready under ${projectpath}/target/coverage-reports/jacoco-ut***********"

pause


