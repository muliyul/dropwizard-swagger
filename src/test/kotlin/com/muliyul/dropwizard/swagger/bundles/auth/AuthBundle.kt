package com.muliyul.dropwizard.swagger.bundles.auth

import com.muliyul.dropwizard.swagger.*
import com.muliyul.dropwizard.swagger.ext.*
import io.dropwizard.*
import io.dropwizard.auth.*
import io.dropwizard.auth.basic.*
import io.dropwizard.auth.chained.*
import io.dropwizard.auth.oauth.*
import io.dropwizard.setup.*
import org.glassfish.jersey.server.filter.*


class AuthBundle<C : Configuration> : ConfiguredBundle<C> {

	override fun run(configuration: C, environment: Environment) {
		environment.jersey().run {
			val basicAuthFilter = BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator {
					when {
						it.password.contains("secret") -> User("basic-user-${it.username}")
						else -> null
					}.toOptional()
				}
				.setPrefix("Basic")
				.buildAuthFilter()

			val oauthAuthFilter = OAuthCredentialAuthFilter.Builder<User>()
				.setAuthenticator {
					when {
						it.contains("secret") -> User("oauth-user-$it")
						else -> null
					}.toOptional()
				}
				.setPrefix("Bearer")
				.buildAuthFilter()

			val apiKeyAuthFilter = ApiKeyCredentialAuthFilter.Builder()
				.setAuthenticator {
					when {
						it.contains("secret") -> User("api_key-user-$it")
						else -> null
					}.toOptional()
				}
				.setPrefix("Api")
				.buildAuthFilter()


			register(RolesAllowedDynamicFeature::class.java)
			register(
				AuthDynamicFeature(
					ChainedAuthFilter<String, User>(
						listOf(
							apiKeyAuthFilter,
							basicAuthFilter,
							oauthAuthFilter
						)
					)
				)
			)
			register(AuthValueFactoryProvider.Binder(User::class.java))
		}
	}

}
