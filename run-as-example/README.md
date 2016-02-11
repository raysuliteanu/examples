[Spring Security](http://projects.spring.io/spring-security/) provides a feature to temporarily change the current authentication token,
typically with the intent to modify the roles thereby granting different, possibly increased, capabilities to the user. They call this
feature "RunAs." See http://docs.spring.io/spring-security/site/docs/4.0.3.RELEASE/reference/htmlsingle/#runas.

All the examples in the documentation are based on Spring XML configuration. I did not see anything that described the Java Config. So
I decided to figure it out myself and wrote this example to do so. Since I also wanted to verify how it worked in conjunction with the
standard filter authentication for web requests, I also used 
[Spring Data REST](http://docs.spring.io/spring-data/rest/docs/2.4.2.RELEASE/reference/html/)
(backed by [Spring Data JPA](http://docs.spring.io/spring-data/jpa/docs/1.9.2.RELEASE/reference/html/)). And because it makes writing 
Spring applications so much easier, the example leverages 
[Spring Boot](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).

The Spring Boot application class is RunAsDemoApplication.java. The Spring Security configuration is contained within.

So that there would be something to pass around in the REST calls there's an Account class, along with an AccountRepository interface.
While basic CRUD is handled by Spring Data REST, I created a separate controller and service - SomeController and SomeService 
respectively. It is the SomeService class that has the method level security annotation that requires the special "run as" privilege.

To run the example you can simply do
```
$ mvn spring-boot:run
```
