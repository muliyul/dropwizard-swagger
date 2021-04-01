package com.muliyul.dropwizard.swagger.resource

import io.swagger.v3.jaxrs2.integration.resources.*
import io.swagger.v3.oas.annotations.*
import javax.servlet.*
import javax.ws.rs.*
import javax.ws.rs.core.*

@Path("/openapi.{type:json|yaml}")
class DropwizardOpenApiResource : BaseOpenApiResource() {

	@GET
	@Produces(MediaType.APPLICATION_JSON, "application/yaml")
	@Operation(hidden = true)
	fun getOpenApi(
		@Context headers: HttpHeaders,
		@Context uriInfo: UriInfo,
		@PathParam("type") type: String,
		@Context config: ServletConfig,
		@Context app: Application
	): Response {
		return super.getOpenApi(headers, config, app, uriInfo, type)
	}

}
