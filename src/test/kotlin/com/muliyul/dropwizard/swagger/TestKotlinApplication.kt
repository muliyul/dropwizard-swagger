package com.muliyul.dropwizard.swagger

import com.muliyul.dropwizard.swagger.bundles.auth.*
import io.dropwizard.*
import io.dropwizard.configuration.*
import io.dropwizard.setup.*

class TestKotlinApplication : Application<TestKotlinConfiguration>() {
	override fun initialize(bootstrap: Bootstrap<TestKotlinConfiguration>) {
		bootstrap.configurationSourceProvider = ResourceConfigurationSourceProvider()
		bootstrap.addBundle(SwaggerBundle())
		bootstrap.addBundle(AuthBundle())
	}

	override fun run(configuration: TestKotlinConfiguration, environment: Environment) {
		environment.jersey().register(TestResource())
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) = TestKotlinApplication().run(*args)
	}
}

