
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

# Provider selection
Use application.properties file to switch the provider,
by default service ueses spendgrid provider i.e ```spendgrid.enabled=true```,
if this flag is false snailgun provider is used

# Stack selection
1. Language: java
2. Framework: springboot
3. Selection criteria:
```
  i.  Allow loose coupling through dependency injection.
  ii. Strict typeing to ensure data integrity 
  iii. Polymorphism and inharetance to promote code reuse where possible
      and allows implementation to vary when needed at runtime 
  vi. Matured libraries/framework.
  v. Better object processing/transforming ability. 
  vi. Schema validation and error handler support
```

# TradeOffs/TODOs
1. Implement Tests
2. Implement get endpoint to query status of async jobs

# Anything else to include??
1. This may be out of scope of the assignment,
   however, another approach to implement fallback would be to
   internally redirect to secondary provider if the primary provider fails/unavailable.
2. Integrating with key management system to dynamically change configuration without application redeploy.

