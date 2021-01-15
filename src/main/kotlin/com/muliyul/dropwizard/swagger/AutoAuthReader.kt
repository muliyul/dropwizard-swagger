package com.muliyul.dropwizard.swagger

import com.fasterxml.jackson.annotation.*
import io.dropwizard.auth.*
import io.swagger.v3.jaxrs2.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.security.*
import java.lang.reflect.*
import javax.ws.rs.*

open class AutoAuthReader : Reader() {
	@Suppress("unused")
	@field:Hidden
	private val annHolder: String? = null

	override fun getParameters(
		type: Type?,
		annotations: MutableList<Annotation>?,
		operation: Operation,
		classConsumes: Consumes?,
		methodConsumes: Consumes?,
		jsonViewAnnotation: JsonView?
	): ResolvedParameter {
		val isAuthParam = annotations?.any { it.annotationClass == Auth::class } == true

		return if (isAuthParam) super.getParameters(
			type,
			annotations,
			operation,
			classConsumes,
			methodConsumes,
			jsonViewAnnotation
		).apply {
			// Instructs swagger-core to ignore this param as a body param
			requestBody = null
		}
		else super.getParameters(
			type,
			annotations,
			operation,
			classConsumes,
			methodConsumes,
			jsonViewAnnotation
		)
	}
}
