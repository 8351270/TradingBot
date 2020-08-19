# TradingBot

This project is a simple Spring Boot (2.4) application for Trading on the platform zubr.io
It connects with the exchange via WebSocket, 
Subscribes to all the channels but only implements a simple logic based on order books. 

There are two environments
dev: pointing to uat environment 
prod: pointing to production environment 

To build the application from source code  you need to have maven installed (maven.apache.org)
inside the project main folder build with the command:

./mvnw clean install

this will generate the .jar file

To run the jar and set dev environment:

java -jar -Dspring.profiles.active=dev target/Trading-Bot-0.0.1-SNAPSHOT.jar

for production:

java -jar -Dspring.profiles.active=prod target/Trading-Bot-0.0.1-SNAPSHOT.jar

websocket specifications can be found on: 
https://spec.zubr.io/#websocket-api

App allows to subscribe to all exchange websocket channels. Subscription to channels orderbooks, orders, ordersFills and balance are mandatory to allow the Bot to start trading since it will only start trading once it receive a message from each of those channels. 

Packages description:

All entities under “entity” package were created from the json messages on the api documentation using the service http://www.jsonschema2pojo.org/ to create the Java objects to map from/to JSON messages.

Under the package “service.channel” there is a Spring service for each channel that will process the messages on that channel. The Spring services under the package “service” will take care of channels subscription, authentication, trading and message resolving to call the channel specific service. 

Trading Bot will execute on a single thread, re connect, read active orders and keep trading in case of connection lost. Also it will cancel all open orders on shutdown. Code related with this functionalities can be found under “websocket” package.





