# Spring Cloud Gateway Query Length Example

An example project that illustrates how to configure Netty server that [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) is based on to accept long query parameters.

# Use-case

By default, Spring Cloud Gateway project can handle HTTP requests with around 8000 characters worth of query parameters. In this case app is required to handle larger query parameters.

# Getting started

In case you want to run the app yourself, feel free to clone the git repo and play around with it.

### Prerequisites

This projct uses:

* [Java 14](https://openjdk.java.net/projects/jdk/14/)
* [Maven](https://maven.apache.org/)

### Installing

Like with any maven project, you can:

```shell script
$ mvn clean install
```

## Testing

Tests use  [WireMock](http://wiremock.org/) to simulate the downstream server and [`WebTestClient`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/reactive/server/WebTestClient.html) to simulate the client. Test boots up the app and then makes HTTP requests, which are proxied to the WireMock server.

You an run test with maven:

```shell script
$ mvn test
```

## Author

* [Josip Antoliš](https://github.com/Antolius)