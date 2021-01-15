package com.muliyul.dropwizard.swagger

import com.muliyul.dropwizard.swagger.ui.*
import io.dropwizard.*
import io.dropwizard.Configuration
import io.dropwizard.assets.*
import io.dropwizard.setup.*
import io.swagger.v3.jaxrs2.integration.*
import io.swagger.v3.jaxrs2.integration.resources.*

class SwaggerBundle<C>(
	private val resourcesPackageNames: Set<String>,
	private val uiConfiguration: SwaggerUiConfiguration = SwaggerUiConfiguration()
) : ConfiguredBundle<C> where C : Configuration, C : SwaggerConfiguration {

	constructor(
		vararg resourcesPackageNames: String,
		uiConfiguration: SwaggerUiConfiguration = SwaggerUiConfiguration()
	) : this(resourcesPackageNames.toSet(), uiConfiguration)

	override fun initialize(bootstrap: Bootstrap<*>) {
		bootstrap.addBundle(AssetsBundle("/static/swagger-ui/", "/swagger-ui/", null, "swagger"))
	}

	override fun run(configuration: C, environment: Environment) {

		val servletConfig = environment.jerseyServletContainer!!.servletConfig
		val swaggerConfiguration = configuration.swaggerConfiguration?.apply {
			// TODO: check if dropwizard-auth is on the classpath
			readerClass = AutoAuthReader::class.qualifiedName
		}

		environment.jersey().run {
			JaxrsOpenApiContextBuilder<JaxrsOpenApiContextBuilder<*>>()
				.servletConfig(servletConfig)
				.application(resourceConfig)
				.resourcePackages(resourcesPackageNames)
				.openApiConfiguration(swaggerConfiguration)
				.ctxId(ServletConfigContextUtils.getContextIdFromServletConfig(servletConfig))
				.buildContext(true)

			register(AcceptHeaderOpenApiResource().resourcePackages(resourcesPackageNames))
			register(OpenApiResource().resourcePackages(resourcesPackageNames))
			register(SwaggerResource(environment.objectMapper, uiConfiguration))
		}
	}
}
