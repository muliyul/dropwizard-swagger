package com.muliyul.dropwizard.swagger

import io.dropwizard.auth.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.security.*
import javax.ws.rs.*
import javax.ws.rs.core.*

@Path("/")
class TestResource {
	@GET
	@Path("/sanity")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.TEXT_HTML)
	fun sanity() = "<html><head></head><body>Sanity</body></html>"

	@GET
	@Path("/ignoring_auth")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.TEXT_HTML)
	@Operation(security = [SecurityRequirement(name = "petstore_auth")])
	fun ignoring_auth(@Auth user: User) = "<html><head></head><body>Sanity</body></html>"
}

