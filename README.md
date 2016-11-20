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

## Performance

The test are showing that the algorithm is O(n) because time is growing linearly when data is doubled in size.

```
average with numElems= 1000: -> 188
average with numElems= 2000: -> 87
average with numElems= 4000: -> 164
average with numElems= 8000: -> 274
average with numElems= 16000: -> 547
average with numElems= 32000: -> 1182
average with numElems= 64000: -> 2171
average with numElems= 128000: -> 4514
average with numElems= 256000: -> 8566
average with numElems= 512000: -> 17765
average with numElems= 1024000: -> 36853
average with numElems= 2048000: -> 77781
average with numElems= 4096000: -> 177106
```
