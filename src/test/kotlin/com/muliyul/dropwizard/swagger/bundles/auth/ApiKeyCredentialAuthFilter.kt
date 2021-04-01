package com.muliyul.dropwizard.swagger.bundles.auth

import com.muliyul.dropwizard.swagger.*
import javax.ws.rs.container.*

class ApiKeyCredentialAuthFilter : AbstractAuthFilter<String, User>("apiKey") {

	override fun retrieveCredentials(requestContext: ContainerRequestContext): String =
		requestContext.getHeaderString("api_key") ?: throwUnauthorizedException()

	class Builder : GenericAuthFilterBuilder<String, User, ApiKeyCredentialAuthFilter>() {
		override fun newInstance(): ApiKeyCredentialAuthFilter = ApiKeyCredentialAuthFilter()
	}

}
