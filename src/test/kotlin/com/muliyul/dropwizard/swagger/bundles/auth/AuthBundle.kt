package com.muliyul.dropwizard.swagger.bundles.auth

import com.muliyul.dropwizard.swagger.*
import io.dropwizard.*
import io.dropwizard.auth.*
import io.dropwizard.auth.basic.*
import io.dropwizard.setup.*


class AuthBundle<C : Configuration> : ConfiguredBundle<C> {

	override fun run(configuration: C, environment: Environment) {
		environment.jersey().run {
			val basicAuthFilter = BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator(BasicAuthenticator)
				.setPrefix("Basic")
				.buildAuthFilter()

			register(AuthDynamicFeature(basicAuthFilter))
			register(AuthValueFactoryProvider.Binder(User::class.java))
		}
	}

}

