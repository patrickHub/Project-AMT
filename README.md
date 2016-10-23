# Project-AMT-2016

## Introduction

This repo contains artifacts to illustrate  java EE development cycle:

* **developers**  You should have a Docker-Compose or Docker Toolbox in your environnment computer (such as Windows 10) to execute the app on the server.

## Quick start

Assuming that you have installed **docker** and **docker-compose** on your machine, move to the `topology-amt` directory and fire up docker-compose to start the wildfly , mysql and phpmyadmin

```
cd topology-amt
docker-compose up --build
```
When the process is done, you should be able to access the app servers at the following URLs:

```
Phpmyadmin -> http://localhost:6060 
Mysql -> http://localhost:3306
JBoss Wildfly -> http://localhost:9090 (apps) and http:localhost:9990 (console -> admin/admin)
Project-AMT-1.0-SNAPSHOT -> http://localhost:9090/Project-AMT-1.0-SNAPSHOT/
```
When you open the wilfly Console admin You will find 1 apps (Project-AMT-1.0-SNAPSHOT.was) in the deployments. then you can just run the 
app at the http://localhost:9090/Project-AMT-1.0-SNAPSHOT/ url.

## Detailed information
- The first time that you open the app on you navigator, the app will requered you to authenticated. you can create your a new user or can authenticate your self by enter patrick as username and patrick as password.
- Phpmyadmin login is root and the password is P@trick29

You can make http request to the app by using Postman if you already have one install in you computer. Clik on the link below and you will import the prepared http request that you can make to save user, to get all the users or a sngle user by his id, to update and to delete a user by his id. This is done test REST api. You should notice that that if you get user by enter an id or a login which don't exist, an error will be throw in the server logs to let you nown that the ressource don't exist. The same thing happens when you register a user with a login that already exist in the database. Then when you make a request, always get to looks in the server log's to be sure that everything goes succefully. 
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/9568f96cf888f39e5db9#?env%5BProject-AMT%5D=W3sidHlwZSI6InRleHQiLCJlbmFibGVkIjp0cnVlLCJrZXkiOiJVUkxfUFJFRklYIiwidmFsdWUiOiJodHRwOi8vbG9jYWxob3N0OjkwOTAvUHJvamVjdC1BTVQtMS4wLVNOQVBTSE9UIn0seyJrZXkiOiIiLCJ2YWx1ZSI6IiIsInR5cGUiOiJ0ZXh0IiwiZW5hYmxlZCI6dHJ1ZX1d)
## Modifying the code

You can also look at the code of the apps and change it, The easiest way is use IntelliJ open the project as an existing project. Then clean and build the app to get the .war file and you will be able to upload it via the console.

