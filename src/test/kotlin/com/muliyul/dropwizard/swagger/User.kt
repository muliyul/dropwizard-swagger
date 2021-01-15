package com.muliyul.dropwizard.swagger

import java.security.*

class User: Principal {
	override fun getName(): String = toString()
}
