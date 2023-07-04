# cyber-test

Build Jars
```code
cd manager
mvn clean compile assembly:single
cd ../calculator
mvn clean compile assembly:single
```

Run containers
```
docker-compose up -d --build
```

Wrong CREATE calculations:
``` 
 curl -X POST -H "Content-Type: application/json" -d '{"operation":"1+2*(32+12"}' http://localhost:80/calculations -v 

curl -X POST -H "Content-Type: application/json" -d '{"operation":"12 2*(32+12"}' http://localhost:80/calculations -v
```

Good CREATE calculations:
```
curl -X POST -H "Content-Type: application/json" -d '{"operation":"1+2*(32+12)"}' http://localhost:80/calculations -v
curl -X POST -H "Content-Type: application/json" -d '{"operation":"1+2*(32+12)*1.2"}' http://localhost:80/calculations -v
```

Response:
```
{"id":"64a450dbb0c772431cc7ae4a","operation":"1+2*(32+12)*5","result":441.0}`
```
GET changing ID with id in JSON response:
```
 curl -X GET -H "Content-Type: application/json" http://localhost:80/calculations/ID -v
```


UPDATE changing ID with id in JSON response:
```
 curl -X PUT -H "Content-Type: application/json" -d '{"operation":"1+2*(32+12)*0"}' http://localhost:80/calculations/ID -v
```

DELETE changing ID with id in JSON response:

```
curl -X DELETE -H "Content-Type: application/json" http://localhost:80/calculations/
```

