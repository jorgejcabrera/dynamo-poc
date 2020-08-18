# Dynamo-POC

It's a simple project to try some DynamoDB key features and take performance measurement. Therefore, I've created a Book entity that looks like this:
```
{
    title: String,
    category: String,
    price: Double,
    rating: Int,
    created_date: Date    
}
```
After that, a book collection was created and saved in a local Dynamo and local MySql.

## Requirements
- Kotlin
- Docker
- MySql
- Dynamo

## Installation
To clone and run this application, you'll need [Git](https://git-scm.com) and [Docker](https://www.docker.com/get-started) installed on your computer. From your command line:
 ```bash
$ docker-compose build 
 ```
and then
 ```bash
$ docker-compose up -d
 ```
The API will listen on `localhost:8080`.

## Examples
```bash
$ curl -X GET \
  http://localhost:8080/database/measures
```