# spring-boot example
This is a small RESTFul application

## REST API
Supports next REST operations:
 - *GET /api/stocks* - To get all stocks' info  
 Example of output json:
 > [{"id":1,"name":"Stock1.L","currentPrice":2.0,"lastUpdate":"2020-05-01T22:56:04Z"},{"id":2,"name":"Stock2.N","currentPrice":1.92,"lastUpdate":"2020-05-02T21:08:47Z"}]
 - *GET /api/stocks/{id}* - To get info about particular stock by id  
 Example of output json:
 > {"id":2,"name":"Stock2.N","currentPrice":1.92,"lastUpdate":"2020-05-02T21:08:47Z"}
 - *POST /api/stocks* - Add a new stock 
 Example of request json:
  > {"name":"Stock3.H", "price":0.23}
 
## How to start the app
> mvn clean install

To start an app on :8080 port:
> java -jar target/spring-boot-1.0.jar

Or to start an app on a custom port:
> java -jar target/spring-boot-1.0.jar --server.port=8888