# Employee API

You need to **Java 11** first for running the application.

If you have to install it first. Have a look to [SdkMan](https://sdkman.io).

## Step 1

Run application by `./mvnw spring-boot:run` command

## Step 2

Swagger UI available:

    http://localhost:8080/api/swagger-ui.html  
    
Also, you can try test API there as well instead of Postman.    

## Step 3

Spring Security is configured. So you need to authenticate first to 

    http://localhost:8080/api/authenticate
    
With following credentials    

    username: harry
    password: potter
    
You could use [HTTP client](https://httpie.org/) for this as well 

    http :8080/api/authenticate username=harry password=potter
    
    HTTP/1.1 200 
    Authorization: <token-value-here>
    ...
    
    {
        "token": "<token-value-here>"
    }
    

## Step 4

Any next request should contain that token at header
 
### Find one employee
    
    http :8080/api/v1/employees/4 Authorization:Bearer\ <token-value-here>
    
    HTTP/1.1 200 
    ...
    
    {
        "active": true, 
        "departmentId": 1, 
        "departmentName": "Gryffindor", 
        "id": 4, 
        "name": "Hagrid"
    }
    
### Create new employee

    http :8080/api/v1/employees Authorization:Bearer\ <token-value-here> name=Rabbit active=true departmentId=2 departmentName=Hufflepuff
    
    HTTP/1.1 201 
    ...
    
    {
        "active": true, 
        "departmentId": 2, 
        "departmentName": "Hufflepuff", 
        "id": 19, 
        "name": "Rabbit"
    }
    
### Update newly created employee

    http PUT :8080/api/v1/employees/19 Authorization:Bearer\ <token-value-here> id=19 name=JoJo\ Rabbit active=false departmentId=1 departmentName=Gryffindor
    
    HTTP/1.1 200 
    
    {
        "active": false, 
        "departmentId": 1, 
        "departmentName": "Gryffindor", 
        "id": 19, 
        "name": "JoJo Rabbit"
    }
    
### Search for all employees which name starts with 'r' letter

    http :8080/api/v1/employees/search?name=r Authorization:Bearer\ <token-value-here>
    
    HTTP/1.1 200 
    
    {
        "content": [
            {
                "active": false, 
                "departmentId": 3, 
                "departmentName": "Ravenclaw", 
                "id": 6, 
                "name": "Robert"
            }
        ], 
        "empty": false, 
        "first": false, 
        "last": false, 
        "number": 1, 
        "numberOfElements": 1, 
        "pageable": {
            "offset": 1, 
            "pageNumber": 1, 
            "pageSize": 1, 
            "paged": true, 
            "sort": {
                "empty": true, 
                "sorted": false, 
                "unsorted": true
            }, 
            "unpaged": false
        }, 
        "size": 1, 
        "sort": {
            "empty": true, 
            "sorted": false, 
            "unsorted": true
        }, 
        "totalElements": 18, 
        "totalPages": 18
    }

### Delete employee

    http DELETE :8080/api/v1/employees/19 Authorization:Bearer\ eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXJyeSIsImV4cCI6MTU4NzgwNzg5MSwiaWF0IjoxNTg3NjM1MDkxfQ.BF6VIBJuiOtQ-1MN5baX0184l4TsCGAXuQRtY2_-ywk
    
    HTTP/1.1 204 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Connection: keep-alive
    Date: Thu, 23 Apr 2020 10:32:13 GMT
    Expires: 0
    Keep-Alive: timeout=60
    Pragma: no-cache
    X-Content-Type-Options: nosniff
    X-Frame-Options: DENY
    X-XSS-Protection: 1; mode=block


