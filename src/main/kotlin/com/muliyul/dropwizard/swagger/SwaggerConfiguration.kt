package com.muliyul.dropwizard.swagger

import com.muliyul.dropwizard.swagger.ui.*
import io.swagger.v3.oas.integration.SwaggerConfiguration

interface SwaggerConfiguration {
	val swaggerConfiguration: SwaggerConfiguration?
//	fun getSwaggerConfiguration(): SwaggerConfiguration? = null
//	fun getSwaggerUiConfiguration(): SwaggerUiConfiguration? = null
	val swaggerUiConfiguration: SwaggerUiConfiguration?
}
