# cnj-security-oidc-backend-micro

Cloud native Java backend exposing REST endpoints protected by OpenID Connect security based on Eclipse MicroProfile.
In this showcase, OpenID Connect security is added by using MicroProfile JWT Auth.

## Status

![Build status]()

## Release Information

A changelog can be found in [changelog.md](changelog.md).

## Build this application

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing a Payara Micro application.
