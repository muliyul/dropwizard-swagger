package com.muliyul.dropwizard.swagger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.muliyul.dropwizard.swagger.ui.configuration.SwaggerUiConfiguration;
import io.dropwizard.Configuration;
import org.jetbrains.annotations.Nullable;

public class TestJavaConfiguration extends Configuration implements SwaggerBundleConfiguration {
    @JsonProperty("swagger-ui")
    private SwaggerUiConfiguration swaggerUiConfiguration = null;

    @Nullable
    @Override
    public SwaggerUiConfiguration getSwaggerUiConfiguration() {
        return swaggerUiConfiguration;
    }
}
