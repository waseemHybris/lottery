Assumptions: 
- If a ticket is purchased and won we assume that the prize is being claimed.
- After each draw we reset the tickets.
- Money Pot carries on unless a reset was processed.


Required Technology:
- Assumes you have installed and configured **Maven** and **JAVA8**
- PORT **8080** is free
- Can run Spring Boot application.

Instructions:

*BUILD it:*

unzip and navigate with your command line to the lottery folder
- > mvn clean install

Will run tests (unit and integration), 
Will install the lottery snapshot jar to the .m2 repository folder

*RUN it:*

In your command line:
for MAC:
> java -jar target/lottery-0.0.1-SNAPSHOT.jar

or using Spring Boot Maven plugin
> mvn spring-boot:run

Follow instructions on console..

Or simply import project to your preferred idea (Eclipse or Intellij) and run it from there.

Note:
Functionality also exposed via rest so you can use the attached postman collection to make rest calls

`POST http://localhost:8080/lottery/purchase/{buyersName}`

`POST http://localhost:8080/lottery/draw`

`GET http://localhost:8080/lottery/winners`

`POST http://localhost:8080/lottery/reset`

`GET http://localhost:8080/lottery/tickets`

`GET http://localhost:8080/lottery/purchased-tickets`

**CAN BE IMPROVED**

- Validation on the buyer name
