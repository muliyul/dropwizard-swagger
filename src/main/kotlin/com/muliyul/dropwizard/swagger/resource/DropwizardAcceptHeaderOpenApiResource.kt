package com.muliyul.dropwizard.swagger.resource

import io.swagger.v3.jaxrs2.integration.resources.*
import io.swagger.v3.oas.annotations.*
import javax.servlet.*
import javax.ws.rs.*
import javax.ws.rs.core.*

@Path("/openapi")
class DropwizardAcceptHeaderOpenApiResource : BaseOpenApiResource() {

	@GET
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(hidden = true)
	fun getOpenApiJson(
		@Context headers: HttpHeaders,
		@Context uriInfo: UriInfo,
		@Context config: ServletConfig,
		@Context app: Application
	): Response {
		return super.getOpenApi(headers, config, app, uriInfo, "json")
	}

	@GET
	@Consumes(MediaType.WILDCARD)
	@Produces("application/yaml")
	@Operation(hidden = true)
	fun getOpenApiYaml(
		@Context headers: HttpHeaders,
		@Context uriInfo: UriInfo,
		@Context config: ServletConfig,
		@Context app: Application
	): Response {
		return super.getOpenApi(headers, config, app, uriInfo, "yaml")
	}

}
