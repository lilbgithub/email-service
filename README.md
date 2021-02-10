
# Email service
Build with `./gradlew build`.

Run with `./gradlew bootRun`.

Service URI [http://localhost:8080/emails](http://localhost:8080/emails)

# API Details
Send email using following POST request body
```sh
{
    "to_name": "bob",
    "to": "bob@gmail.com",
    "from": "john.dc@gmail.com",
    "from_name": "john",
    "subject": "hello there",
    "body": "some message"
}
```

Success response code:

```sh
200 for sync response
202 for async response
```

Error Codes:
```sh
4xx Client error
5xx Server error 
```
# How to switch email provider?
Use application.properties file's ```spendgrid.enabled``` to switch the provider,
by default service is configured to use ```spendgrid``` provider i.e ```spendgrid.enabled=true```,
set this flag to false to switch to ```snailgun```.

# Stack selection
1. Language: java
2. Framework: springboot
3. Selection criteria:
```
  i.  Allow loose coupling through dependency injection.
  ii. Strict typeing to ensure data integrity 
  iii. Polymorphism and inharetance to promote code reuse where possible
      and allow implementation to vary when needed at runtime 
  vi. Mature libraries/framework.
  v. Better object processing/transforming ability. 
  vi. Robust validation and error handling support.
```

# TradeOffs/TODOs
1. Implement get endpoint to query status of async jobs
2. Implement integration tests

# Anything else to include??
1. This may be out of scope of the assignment,
   however, another approach to implement fallback would be to
   internally redirect to secondary provider if the primary provider fails/unavailable.
2. Integrating with key management system to dynamically change configuration without application redeploy.

