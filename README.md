Assumptions: 
- If a ticket is purchased and won we assume that the prize is being claimed.
- After each Draw we reset the tickets.
- Draw Function is generous and will give the win to the purchased ticket


TO RUN:
Assumes that you have Maven and JAVA8
PORT 8080 is free
can run Spring Boot application

unzip and navigate with your command line to the lottery folder

mvn clean install

that should run successfully given you have maven and .m2 configered locally
Will run tests scenarios
will install the jar to the m2 repository folder


to run:

run the command in console:
java -jar ~/.m2/repository/com/esignlive/lottery/0.0.1-SNAPSHOT/lottery-0.0.1-SNAPSHOT.jar

or import project to your idea and run it from there.
(was tested on Intellij on MAC)

and then use read the promoted msgs

---

functionality also exposed via rest. 

can run the following calls given that the application is running
-
-
-





