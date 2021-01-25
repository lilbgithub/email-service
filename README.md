
# Email service
Run with `./gradlew bootRun`.
Run with `./gradlew build`.
 Open [http://localhost:8080/emails](http://localhost:8080/emails) after startup.

#Stack selection
1. Language: java
2. Framework: springboot
3. Reason:
  a. Supports/Enforces the strict schema check 
  b. Spring dependency injection allows loose coupling
  c. Java polymorphism/inharetance promotes code reuse and allows implementation to change at runtime 
  d. Matured libraries/framework.
  e. Provides better object processing/transforming ability. 

#TradeOffs/TODOs
1. Implement Tests
2. Implement get endpoint to query status of async emails

#Anything else to include??
1. This may be out of scope of the assignment,
   however, another approach to implement fallback would be to
   internally redirect to secondary provider if the primary provider fails/unavailable.
2. Integrating with key managment system to dynamically changing configuration without application redeploy.

