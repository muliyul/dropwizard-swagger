package com.muliyul.dropwizard.swagger

import com.muliyul.dropwizard.swagger.ui.*
import com.muliyul.dropwizard.swagger.ui.configuration.*
import io.dropwizard.*
import io.dropwizard.Configuration
import io.dropwizard.assets.*
import io.dropwizard.setup.*
import io.swagger.v3.jaxrs2.integration.*
import io.swagger.v3.oas.integration.*
import com.muliyul.dropwizard.swagger.resource.*


class SwaggerBundle<C> @JvmOverloads constructor(
	private val swaggerUiConfiguration: SwaggerUiConfiguration = SwaggerUiConfiguration()
) : ConfiguredBundle<C> where C : Configuration, C : SwaggerBundleConfiguration {

	override fun initialize(bootstrap: Bootstrap<*>) {
		bootstrap.addBundle(AssetsBundle("/static/swagger-ui/", "/swagger-ui/", null, "swagger"))
	}

	override fun run(configuration: C, environment: Environment) {
		val swaggerUiConfiguration = configuration.swaggerUiConfiguration ?: swaggerUiConfiguration

		val resourceConfig = environment.jersey().resourceConfig
		val ctx = JaxrsOpenApiContextBuilder<JaxrsOpenApiContextBuilder<*>>()
			.application(resourceConfig)
			.buildContext(false)
			.apply {
				setOpenApiScanner(DropwizardCompatibleScanner())
				setOpenApiReader(DropwizardCompatibleReader())
			}
			.init()

		val resources = listOf(
			DropwizardAcceptHeaderOpenApiResource(),
			DropwizardOpenApiResource()
		)

		environment.jersey().apply {
			resources.forEach { resource ->
				val swaggerConfiguration = ctx.openApiConfiguration as SwaggerConfiguration
				resource.openApiConfiguration = swaggerConfiguration.apply {
					scannerClass = scannerClass ?: DropwizardCompatibleScanner::class.qualifiedName
					readerClass = readerClass ?: DropwizardCompatibleReader::class.qualifiedName
				}
				register(resource)
			}
			if (!swaggerUiConfiguration.disabled) {
				register(SwaggerResource(environment.objectMapper, swaggerUiConfiguration))
			}
		}
	}

}
