##### Scala log parser
This project have been created for exposing via HttpApi and saving in mongodb log file data.

``class Parser``is responsible for parsing Logfile and saving into mongodb.

``class ResidentService`` is responsible for counting average from log file.

``class ResidentDAO`` is responsible for communication with mongodb.

``class HomeComtroller`` is used to handle http requests

``file Routes`` contains two endpoints

``/allResidents`` showing all saved lines from file

``/:eventType/average/from=:from&to=:to`` showing average from log file.

**To run project you should enter in command line:**
For Docker to run MongoDB:
```
cd ci-cd/dev-compose
docker-compose up -d
```
``file application.conf`` you can change uri for mongodb

For running sbt project:
```
sbt compile
sbt run
```

