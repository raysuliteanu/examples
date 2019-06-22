# Okta OIDC Demo
This example is based on an Okta example (link forthcoming). It uses an Okta
developer account. To use yourself you will need to create one. This is an
evolving WIP with the intent to show an approach to multitenancy with
OIDC/OAuth2.

## Building
```bash
$ ./gradlew build
```

## Running
### With Gradle
In one terminal window
```bash
$ ./gradlew :client:bootRun
```
In a separate terminal window
```bash
$ ./gradlew :server:bootRun
```

### Command-line
In one terminal window
```bash
$ java -jar client/build/libs/client-0.0.1-SNAPSHOT.jar
```
In a separate terminal window
```bash
$ java -jar server/build/libs/server-0.0.1-SNAPSHOT.jar
```

## Using
Open a browser and navigate to `localhost:8080`. You should be redirected
to the Okta login page to authenticate. If authentication is successful,
you should be redirected to a page with `Hello, <your user name>`. Now
navigate to `localhost:8080/api` and you should see a result displayed indicating
you've successfully accessed the protected resource on the resource server.

## Debugging
Spring Boot LiveReload is configured, so the apps will be redeployed if running
whenever a build is done. 
