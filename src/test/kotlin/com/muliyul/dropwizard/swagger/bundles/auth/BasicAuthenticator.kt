package com.muliyul.dropwizard.swagger.bundles.auth

import com.muliyul.dropwizard.swagger.*
import io.dropwizard.auth.*
import io.dropwizard.auth.basic.*
import java.util.*

object BasicAuthenticator : Authenticator<BasicCredentials, User> {
	override fun authenticate(credentials: BasicCredentials): Optional<User> =
		if ("secret" == credentials.password) {
			Optional.of(User(credentials.username))
		} else Optional.empty()
}
