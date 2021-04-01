package com.muliyul.dropwizard.swagger

import io.swagger.v3.jaxrs2.integration.*
import io.swagger.v3.oas.integration.api.*

private val ignoredPackages = setOf("com.papertrail.profiler", "org.glassfish.jersey")

private val delegate = JaxrsApplicationAndAnnotationScanner()

open class DropwizardCompatibleScanner : OpenApiScanner by delegate {
	override fun classes(): MutableSet<Class<*>> =
		delegate.classes()
			.filter { c -> ignoredPackages.none { p -> c.name.startsWith(p, ignoreCase = true) } }
			.toMutableSet()
}
