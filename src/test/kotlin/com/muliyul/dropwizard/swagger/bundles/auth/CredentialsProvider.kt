package com.muliyul.dropwizard.swagger.bundles.auth

import javax.ws.rs.container.*

fun interface CredentialsProvider<C> {
	fun retrieveCredentials(requestContext: ContainerRequestContext): C
}
