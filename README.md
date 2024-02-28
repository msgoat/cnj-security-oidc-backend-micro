# cnj-security-oidc-backend-micro

Cloud native Java backend exposing REST endpoints protected 
by [OpenID Connect](https://openid.net/developers/how-connect-works/) 
security based on [MicroProfile JWT Auth](https://download.eclipse.org/microprofile/microprofile-jwt-auth-2.1/microprofile-jwt-auth-spec-2.1.html) feature 
provided by Eclipse MicroProfile.

The application is packaged as a multi-architecture docker image which supports the following platforms:
* linux/amd64
* linux/arm64/v8

## Synopsis

Please check [Maven POM](pom.xml) for details on how-to integrate `MicroProfile JWT Auth`
into your application.

The `MicroProfile JWT Auth` feature adds a token based authentication mechanism to your application
which allows you to protect the REST endpoint exposed by your application. Each HTTP request received by your REST
endpoints is expected to carry a special HTTP header `Authorization` with a so-called bearer token
issued by an external identity provider. The token itself is a standardized JSON Web Token and will be verified
by the `MicroProfile JWT Auth` feature to meet the following requirements:

* Is the JWT token signed by the given identity provider?
* Is the JWT token issued by the given identity provider?
* Does the authenticated user represented by the JWT token have the expected role to call the REST endpoint?

If any verification fails, the request is rejected with either HTTP status code `401` or `403`.

In order to activate the `MicroProfile JWT Auth` feature a `@LoginConfig` annotation needs to be added to 
the JAX-RS application class (see: [JaxRsConfiguration](src/main/java/group/msg/at/cloud/cloudtrain/adapter/rest/JaxRsConfiguration.java)):

```java
@ApplicationScoped
@ApplicationPath("api")
@LoginConfig(authMethod = "MP-JWT")
public class JaxRsConfiguration extends Application {
    
}
```

Additionally, values for at least two configuration parameters must be provided (see: [docker-compose.yml](docker-compose.yml)):

* `MP_JWT_VERIFY_PUBLICKEY_LOCATION` specifies the endpoint of the identity provider which returns the public key set used to check the JWT tokens signature
* `MP_JWT_VERIFY_ISSUER` specifies the ID of the expected JWT token issuer

Within the application, standard mechanisms like `@RolesAllowed` annotations or `SessionContext.isCallerInRole()` methods can be used
to secure access to protected resources.

`MicroProfile JWT Auth` provides an injectable `JsonWebToken` object which allows access
to the JWT token itself:

```java
    @Inject
    JsonWebToken token;
```

> __Attention__: Unfortunately, the `MicroProfile JWT Auth` feature does not promote received JWT tokens to downstream services automatically!

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiOWhMOGVkcWdMbXAvekErVDRnN04rUW9rK1VsTVhjYitNVytzR2trb1c3L1hwUDAza3FQYUUyZERLNi8xR2N0UDg1bE1GbUJzVFV3eUFHOGtMWVhLK0FjPSIsIml2UGFyYW1ldGVyU3BlYyI6ImhRanVsd1RoRWpzUy8wSk0iLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release Information

A changelog can be found in [changelog.md](changelog.md).

## Docker Pull Command

`docker pull docker.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-security-oidc-backend-micro`

## Helm Pull Command

`helm pull oci://docker.cloudtrain.aws.msgoat.eu/cloudtrain-charts/cnj-security-oidc-backend-micro`

## HOW-TO build this application locally

If all prerequisites are met, just run the following Maven command in the project folder:

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing the showcase application.

## HOW-TO start and stop this showcase locally

In order to run the whole showcase locally, just run the following docker commands in the project folder:

```shell 
docker compose up -d
docker compose logs -f 
```

Press `Ctlr+c` to stop tailing the container logs and run the following docker command to stop the show case:

```shell 
docker compose down
```

## HOW-TO demo this showcase

The showcase application will be accessible:
* locally via `http://localhost:38080`
* remotely via `https://train2023-dev.k8s.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-security-oidc-backend-micro` (if the training cluster is up and running)

Send a simple GET request with a bearer token issued by your identity provider to endpoint URI `/api/v1/hello` 
and you will get a personalized welcome message in JSON format:

```json
{ 
  "code":"hello",
  "id":"73127522-d6ca-4a9f-916c-790e3c8aea77",
  "text":"Dear <userName>, welcome to a cloud native Java application based on MicroProfile protected by OpenID Connect!"
}
```
