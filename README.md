# TradingBot

This project is a simple Spring Boot (2.4) application for Trading on the platform zubr.io
It connects with the platform via WebSocket, 
Subscribes to all the channels but only implements a simple logic based on order books. 

There are two environments
dev: pointing to uat environment 
prod: pointing to production environment 

To build the application from source code  you need to have maven installed (maven.apache.org)
inside the project main folder build with the command:
./mvnw 
this will generate the .jar file

To run the jar and set dev environment:

java -jar target/Trading-Bot-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=dev

for production:

java -jar target/Trading-Bot-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod
 
