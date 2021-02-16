package com.muliyul.dropwizard.swagger.ui

import com.fasterxml.jackson.databind.*
import com.muliyul.dropwizard.swagger.ui.configuration.*
import io.swagger.v3.oas.annotations.*
import javax.ws.rs.*
import javax.ws.rs.core.*

@Path("/swagger")
class SwaggerResource(
	private val mapper: ObjectMapper,
	private val swaggerUiConfiguration: SwaggerUiConfiguration
) {
	@get:GET
	@get:Consumes(MediaType.WILDCARD)
	@get:Produces(MediaType.TEXT_HTML)
	@get:Operation(hidden = true)
	val index by lazy {
		val originalIndexInputStream = ClassLoader.getSystemClassLoader()
			.getResourceAsStream("static/swagger-ui/index.html") ?: error("Could not find index.html in the classpath.")

		val originalIndex = originalIndexInputStream
			.reader()
			.readText()
			.replace("./", "./swagger-ui/")

		val swaggerUiConfigurationRegex = """.*\((?<config>\{(.*\s*)*})\).*""".toRegex()
		val (before, after) = swaggerUiConfigurationRegex.split(originalIndex, 2)

		val prettyPrinter = mapper.writer().withDefaultPrettyPrinter()
		"${before}\r\nvar ui = SwaggerUIBundle(${prettyPrinter.writeValueAsString(swaggerUiConfiguration)});\r\n$after"
	}
}
