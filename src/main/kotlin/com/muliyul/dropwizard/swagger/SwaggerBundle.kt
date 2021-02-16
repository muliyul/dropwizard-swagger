package com.muliyul.dropwizard.swagger

import com.muliyul.dropwizard.swagger.ui.*
import com.muliyul.dropwizard.swagger.ui.configuration.*
import io.dropwizard.*
import io.dropwizard.assets.*
import io.dropwizard.setup.*
import io.swagger.v3.jaxrs2.integration.*
import io.swagger.v3.jaxrs2.integration.resources.*


class SwaggerBundle<C> @JvmOverloads constructor(
	private vararg val resourcesPackageNames: String,
	private val swaggerUiConfiguration: SwaggerUiConfiguration = SwaggerUiConfiguration()
) : ConfiguredBundle<C> where C : Configuration, C : SwaggerBundleConfiguration {

	override fun initialize(bootstrap: Bootstrap<*>) {
		bootstrap.addBundle(AssetsBundle("/static/swagger-ui/", "/swagger-ui/", null, "swagger"))
	}

	override fun run(configuration: C, environment: Environment) {
		val swaggerUiConfiguration = configuration.swaggerUiConfiguration ?: swaggerUiConfiguration

		val resourcesPackageNames = resourcesPackageNames.toSet()

		JaxrsOpenApiContextBuilder<JaxrsOpenApiContextBuilder<*>>()
			.application(environment.jersey().resourceConfig)
			.resourcePackages(resourcesPackageNames)
			.buildContext(false)
			.apply {
				setOpenApiScanner(DropwizardCompatibleScanner())
				setOpenApiReader(DropwizardCompatibleReader())
			}
			.init()

		environment.jersey().apply {
			register(AcceptHeaderOpenApiResource().resourcePackages(resourcesPackageNames))
			register(OpenApiResource().resourcePackages(resourcesPackageNames))
			if (!swaggerUiConfiguration.disabled) {
				register(SwaggerResource(environment.objectMapper, swaggerUiConfiguration))
			}
		}
	}

}
