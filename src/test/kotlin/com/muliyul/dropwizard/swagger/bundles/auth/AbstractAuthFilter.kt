package com.muliyul.dropwizard.swagger.bundles.auth

import io.dropwizard.auth.*
import org.slf4j.*
import java.security.*
import javax.ws.rs.*
import javax.ws.rs.container.*

@Suppress("unused")
abstract class AbstractAuthFilter<C, P : Principal>(
	private val scheme: String
) : AuthFilter<C, P>(), CredentialsProvider<C> {

	private val logger = LoggerFactory.getLogger(AbstractAuthFilter::class.java)

	protected fun throwUnauthorizedException(): Nothing =
		throw WebApplicationException(unauthorizedHandler.buildResponse(prefix, realm))

	override fun filter(requestContext: ContainerRequestContext) {
		val credentials = try {
			retrieveCredentials(requestContext)
		} catch (e: Throwable) {
			logger.error("Exception thrown while trying to retrieve credentials!", e)
			throwUnauthorizedException()
		}
		if (!authenticate(requestContext, credentials, scheme)) {
			throwUnauthorizedException()
		}
	}

}
