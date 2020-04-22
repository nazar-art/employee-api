# Employee API

You need to **Java 11** first for running the application.
If you need to install it have a look to [SdkMan](https://sdkman.io).

## Step 1

Run application by `./mvnw spring-boot:run` command

## Step 2

Spring Security is configured. So you need to authenticate first

    POST http://localhost:8080/api/authenticate 
    {
        username: harry
        password: potter
    }
    
Example of call from terminal with [HTTP client](https://httpie.org/) 

    http :8080/api/authenticate username=harry password=potter
    
    HTTP/1.1 200 
    Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1a2Vlc3MiLCJleHAiOjE1ODczNjA1OTAsImlhdCI6MTU4NzMyNDU5MH0.Epf0M-c6sQWWOBLQW3FIhJPffoWys7AjGuIrkH1MgQ0
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Type: application/json;charset=UTF-8
    Date: Sun, 19 Apr 2020 19:29:50 GMT
    Expires: 0
    Pragma: no-cache
    Transfer-Encoding: chunked
    X-Content-Type-Options: nosniff
    X-Frame-Options: DENY
    X-XSS-Protection: 1; mode=block
    
    {
        "token": "<token-value-here>"
    }
    

## Step 3

Any next request should contain that token at header
    
    http :8080/api/v1/employees/4 Authorization:Bearer\ <token-value-here>
    
    HTTP/1.1 200 
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Type: application/json;charset=UTF-8
    Date: Sun, 19 Apr 2020 19:41:57 GMT
    Expires: 0
    Pragma: no-cache
    Transfer-Encoding: chunked
    X-Content-Type-Options: nosniff
    X-Frame-Options: DENY
    X-XSS-Protection: 1; mode=block
    
    {
        "active": true, 
        "department": {
            "id": 1, 
            "name": "Gryffindor"
        }, 
        "id": 4, 
        "name": "Hagrid"
    }

  
---

Swagger UI available:

    http://localhost:8080/api/swagger-ui.html  
    
Also, you can try test API there as well instead of Postman.    

---

### TODO LIST:

#### Backend task

- [x] Add Swagger annotations
- [x] Add jwt security to the application
- [x] Replace response from controller to DTO object
- [x] Replace JPA framework with custom implementation:
    - [x] add search for starts with for employee
    - [ ] check about adding repository level between SERVICE and DAO layer
    - [ ] pagination for API


#### UI tasks

- [ ] Create Angular Client with One page + Ajax 
- [ ] implement Ajax for UI
- [ ] Login for UI 

#### Final step
- [ ] describe how to run everything at readme file
- [ ] zip project with UI
