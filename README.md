# Intervals
## How to use it
1- Generate Jar file
```bash
mvn clean install
```
2- Execute project with parameters
```bash
java -jar target/intervals-0.0.1-SNAPSHOT.jar "[{\"first\":50,\"last\":100},{\"first\":123,\"last\":456}]" "[{\"first\":30,\"last\":80},{\"first\":153,\"last\":406}]"
```