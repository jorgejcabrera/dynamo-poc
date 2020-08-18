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

You can change the items' quantity stored in the Dynamo by updating parameter AAAAAA in the docker-compose.
## Requirements
You only need Docker :)

## Installation
To clone and run this application, you'll need [Git](https://git-scm.com) and [Docker](https://www.docker.com/get-started) installed on your computer. From your command line type:
 ```bash
$ docker-compose build 
 ```
and then
 ```
$ docker-compose up
 ```
The API will listen on `localhost:8080`.

## Examples
Run the following curl to take Dynamo and MySql's measures
```bash
$ curl -X GET \
  http://localhost:8080/database/measures
```