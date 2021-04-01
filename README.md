dropwizard-swagger
==================

A Dropwizard bundle inspired by the original [dropwizard-swagger](https://github.com/federecio/dropwizard-swagger) and its popular [fork](https://github.com/smoketurner/dropwizard-swagger)
that serves [Swagger UI](https://github.com/swagger-api/swagger-ui) and loads [OpenApi](https://github.com/OAI/OpenAPI-Specification) 3.0 (or Swagger2) endpoints.

#### Notable improvements:

- Spec can be defined in standard locations (see [this](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Integration-and-Configuration#known-locations)). `config.yaml` takes precedence.
- UI is configurable (programmatically or via `config.yaml`).
- If `dropwizard-auth` is included, then by default your `@Auth` annotated parameters will be marked as `@Hidden` so they won't interfere with request body.

### Installation

#### Maven
```xml
<dependency>
<groupId>com.muliyul</groupId>
<artifactId>dropwizard-swagger</artifactId>
<version>0.0.1</version>
</dependency>
```

#### Gradle
```groovy
implementation('com.muliyul:dropwizard-swagger:0.0.1')
```

### Usage

See [example](src/test/java/com/muliyul/dropwizard/swagger/TestJavaConfiguration.java)
```java
class AppConfiguration extends Configuration implements com.muliyul.dropwizard.swagger.SwaggerConfiguration {
    @JsonProperty("swagger")
    private SwaggerConfiguration swaggerConfiguration = null;
    @JsonProperty("swagger-ui")
    private SwaggerUiConfiguration swaggerUiConfiguration = null;
    
    public SwaggerConfiguration getSwaggerConfiguration() {
        return swaggerConfiguration;
    }
    
    public SwaggerUiConfiguration getSwaggerUiConfiguration() {
        return swaggerUiConfiguration;
    }
}
```

#### In your Application class:
```java
@Override
public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    bootstrap.addBundle(new SwaggerBundle<>());
    
    // or pass the packages to scan
    // bootstrap.addBundle(new SwaggerBundle("com.example.resources", "com.example.resources2"));
    // or specify them in config.yaml
    // or specify them in one of the known OpenApi spec locations
    // the choice is yours!
}
```

That's it!

The bundle will scan your classpath for any resources and expose them to swagger-ui via `/swagger`.

You can access the complete definitions in `/openapi`, `/openapi.json`, `/openapi.yaml` [see this](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Integration-and-Configuration#openapiresource).

#### Configuring Swagger-UI

See [SwaggerUiConfiguration](src/main/kotlin/com/muliyul/dropwizard/swagger/ui/configuration/SwaggerUiConfiguration.kt)

---
Check out OpenApi spec file/definitions [here]().

Check out the available Swagger-UI options [here]().

---
## Development

Clone the project and run `TestJavaApplication` or `TestKotlinApplication`.
