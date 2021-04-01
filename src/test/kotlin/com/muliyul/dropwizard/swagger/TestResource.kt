package com.muliyul.dropwizard.swagger

import io.dropwizard.auth.*
import io.swagger.v3.oas.annotations.security.*
import javax.ws.rs.*
import javax.ws.rs.core.*

@Path("/")
class TestResource {
	@GET
	@Path("/sanity")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.TEXT_PLAIN)
	fun sanity() = "Sanity check!"

	@GET
	@Path("/greeting")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.TEXT_PLAIN)
	@SecurityRequirement(name = "api_key")
	fun greeting(@Auth user: User) = "Hello ${user.name}!"
}

