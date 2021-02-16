package com.muliyul.dropwizard.swagger

import io.dropwizard.auth.*
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
	@Path("/ignoring_auth")
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.TEXT_PLAIN)
	fun ignoringAuth(@Auth user: User) = "Hello ${user.name}!"
}

