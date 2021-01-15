package com.muliyul.dropwizard.swagger

import com.fasterxml.jackson.annotation.*
import com.muliyul.dropwizard.swagger.ui.*
import io.dropwizard.*

class TestConfiguration(
	@field:JsonProperty("swagger")
	override val swaggerConfiguration: io.swagger.v3.oas.integration.SwaggerConfiguration? = null,
	@field:JsonProperty("swagger-ui")
	override val swaggerUiConfiguration: SwaggerUiConfiguration? = null
) : Configuration(), SwaggerConfiguration
