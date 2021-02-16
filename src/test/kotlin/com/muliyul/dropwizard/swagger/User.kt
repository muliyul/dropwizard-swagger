package com.muliyul.dropwizard.swagger

import java.security.*

class User(
	private val username: String
) : Principal {
	override fun getName(): String = username
}
