Assumptions: 
- If a ticket is purchased and won we assume that the prize is being claimed.
- After each Draw we reset the tickets.
- Draw Function is generous and will give the win to the purchased ticket // TODO


Required Technology:
- Assumes you have installed and configured **Maven** and **JAVA8**
- PORT **8080** is free
- can run Spring Boot application.

Instructions:

*BUILD it:*

unzip and navigate with your command line to the lottery folder
- > mvn clean install

Will run tests (unit and integration), 
Will install the lottery snapshot jar to the .m2 repository folder

*RUN it:*

In your command line:
for MAC:
> java -jar ~/.m2/repository/com/esignlive/lottery/0.0.1-SNAPSHOT/lottery-0.0.1-SNAPSHOT.jar

Follow instructions on console

Or simply import project to your preferred idea (Eclipse or Inellij) and run it from there.

Note:
Functionality also exposed via rest so you can use the attached postman collection to make rest calls

`POST http://localhost:8080/lottery/purchase/{buyersName}`

`POST http://localhost:8080/lottery/draw`

`POST http://localhost:8080/lottery/reset`

`GET http://localhost:8080/lottery/tickets`

`GET http://localhost:8080/lottery/purchased-tickets`

