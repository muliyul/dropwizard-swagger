package com.muliyul.dropwizard.swagger

import com.fasterxml.jackson.annotation.*
import io.dropwizard.auth.*
import io.swagger.v3.jaxrs2.*
import io.swagger.v3.oas.models.*
import io.swagger.v3.oas.models.security.*
import java.lang.reflect.*
import javax.ws.rs.*

/**
 * This class instructs Swagger to ignore [Auth]
 */
open class DropwizardCompatibleReader : Reader() {

	override fun getParameters(
		type: Type?,
		annotations: MutableList<Annotation>?,
		operation: Operation,
		classConsumes: Consumes?,
		methodConsumes: Consumes?,
		jsonViewAnnotation: JsonView?
	): ResolvedParameter {
		val isAuthParam = annotations?.any { it.annotationClass == Auth::class } == true

		return super.getParameters(
			type,
			annotations,
			operation,
			classConsumes,
			methodConsumes,
			jsonViewAnnotation
		).apply {
			// instructs Swagger to ignore @Auth as body param
			if (isAuthParam) requestBody = null
		}
	}

}
