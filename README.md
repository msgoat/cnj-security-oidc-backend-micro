# cnj-security-oidc-backend-micro

Cloud native Java backend exposing REST endpoints protected by OpenID Connect security based on Eclipse MicroProfile.
In this showcase, OpenID Connect security is added by using MicroProfile JWT Auth.

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiOWhMOGVkcWdMbXAvekErVDRnN04rUW9rK1VsTVhjYitNVytzR2trb1c3L1hwUDAza3FQYUUyZERLNi8xR2N0UDg1bE1GbUJzVFV3eUFHOGtMWVhLK0FjPSIsIml2UGFyYW1ldGVyU3BlYyI6ImhRanVsd1RoRWpzUy8wSk0iLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release Information

A changelog can be found in [changelog.md](changelog.md).

## Build this application

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing a Payara Micro application.
