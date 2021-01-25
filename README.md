
# Email service
Run with `./gradlew bootRun`.
Run with `./gradlew build`.
 Service URI [http://localhost:8080/emails](http://localhost:8080/emails) after startup.
```sh
POST Request :
{
    "to_name": "bob",
    "to": "bob@gmail.com",
    "from": "john.dc@gmail.com",
    "from_name": "john",
    "subject": "hello there",
    "body": "some message"
}

POST susccess responses :

200 when sync response
202 when async response

```
# Stack selection
1. Language: java
2. Framework: springboot
3. Selection criteria:
```
  i. Supports/Enforces the strict schema check 
  ii. Spring dependency injection allows loose coupling
  iii.vJava polymorphism/inharetance promotes code reuse and allows implementation to change at runtime 
  vi. Matured libraries/framework.
  v. Provides better object processing/transforming ability. 
```

# TradeOffs/TODOs
1. Implement Tests
2. Implement get endpoint to query status of async jobs

# Anything else to include??
1. This may be out of scope of the assignment,
   however, another approach to implement fallback would be to
   internally redirect to secondary provider if the primary provider fails/unavailable.
2. Integrating with key management system to dynamically change configuration without application redeploy.

