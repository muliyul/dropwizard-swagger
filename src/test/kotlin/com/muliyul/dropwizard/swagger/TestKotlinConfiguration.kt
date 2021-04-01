package com.muliyul.dropwizard.swagger

import com.fasterxml.jackson.annotation.*
import com.muliyul.dropwizard.swagger.ui.configuration.*
import io.dropwizard.*

class TestKotlinConfiguration(
//	@field:JsonProperty("swagger")
//	override val swaggerConfiguration: SwaggerConfiguration? = null,
	@field:JsonProperty("swagger-ui")
	override val swaggerUiConfiguration: SwaggerUiConfiguration? = null
) : Configuration(), SwaggerBundleConfiguration
