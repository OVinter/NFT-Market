# NFT Market

bid - ask 
nft ?
or
arts ?

## Server

* The gateway server is a Java application built with the help of the Spring framework. It will provide the client applications with REST endpoints through which registration, bidding, viewing and submission services are exposed. The payloads will be encoded in json format, and the data transfer objects will mirror the entities stored in the MySQL database.

* To avoid overloading the server when the number of orders (bids and postings) exceed the maximum available throughput offered by the underlying hardware, the gateway server will connect to a Kafka broker and pass the incoming orders as messages on a topic. Before pushing them to Kafka, the orders are enriched with a unique identifier, to be used as a key when referencing them downstream.

* The gateway Java server will handle all user management actions (create user, change password, update shipping address) but will pass the responsibility of order fulfillment to another server, connected to the same kafka broker.

* The processing server is small microservice running node, listening to the kafka topic the gateway server pushes to. It will process the order, call external APIs and save it to in database.

* Since the fulfillment of the order takes place asynchronously, the UI will not be aware (the moment when the gateway server returns the ajax call) whether the order was successfully placed or not. Thus, a polling strategy will be applied by the UI that would call the order/status gateway server endpoint every 2 seconds allowing the end user to be informed of the status of its order in real time.


## Client

* Users will be able to connect to the platform via a web application

* They will have the option to register, view products, submit **nfts/arts** (with pictures), place bids and retrieve other user's email address to contact them.



## Technology

* Back-end -> Java with Spring Boot framework

* Front-end -> Angular

* Database -> MySQL

* Handling real-time data feeds -> Apache Kafka 

