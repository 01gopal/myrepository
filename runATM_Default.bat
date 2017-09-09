REM mvn install will save the target output to %userprofile%\.m2\repository ...

title Project ATM

cd %~dp0

call mvn clean

echo "*********MAVEN Clean completed***********"

call mvn install 


call mvn package -DskipTests=true

echo "*********MAVEN Package completed***********"

REM to start the application

cd %~dp0

java -cp "target/ATM-0.0.1-SNAPSHOT.jar" -Djava.ext.dirs=target/lib com.suncorp.cashman.Application

pause
