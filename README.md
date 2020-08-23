# Dynamo-POC

It's a simple project to try some DynamoDB key features and take performance measurement. Therefore, I have created a Book entity that looks like this:
```
{
    title: String,
    category: String,
    price: Double,
    rating: Int,
    created_date: Date    
}
```
With this pattern, a book collection was created and saved in a local Dynamo and local MySql.
The main purposes are analyzing the latency's changes depending on items amount saved, and measuring the performance of Dynamo's gsi and lsi.

You can change the items' amount stored in the Dynamo by updating parameter ITEMS_AMOUNT in the docker-compose.
```
  app:
    container_name: dynamo-poc
    environment:
      ITEMS_AMOUNT: 10000
```

## Requirements
You only need Docker :+1:

## Installation
To clone and run this application, you'll need [Git](https://git-scm.com) and [Docker](https://www.docker.com/get-started) installed on your computer. From your command line type:
 ```shell script
$ docker-compose build 
 ```
and then
 ```shell script
$ docker-compose up
 ```
The API will listen on `localhost:8080`.

## Examples
Run the following curl to take Dynamo and MySql's measures
```shell script
$ curl -X GET \
  http://localhost:8080/database/measures
```