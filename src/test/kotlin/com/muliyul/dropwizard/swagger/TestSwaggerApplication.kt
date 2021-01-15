package com.muliyul.dropwizard.swagger

import io.dropwizard.*
import io.dropwizard.configuration.*
import io.dropwizard.setup.*

class TestSwaggerApplication: Application<TestConfiguration>() {
	override fun initialize(bootstrap: Bootstrap<TestConfiguration>) {
		bootstrap.configurationSourceProvider = ResourceConfigurationSourceProvider()
		bootstrap.addBundle(SwaggerBundle("com.muliyul.dropwizard.swagger"))
	}

	override fun run(configuration: TestConfiguration, environment: Environment) {
		environment.jersey().register(TestResource())
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) = TestSwaggerApplication().run(*args)
	}
}

