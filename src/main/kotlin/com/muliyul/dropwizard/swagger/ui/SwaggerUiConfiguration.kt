package com.muliyul.dropwizard.swagger.ui

import com.fasterxml.jackson.annotation.*
import javax.ws.rs.*

@JsonInclude(value = JsonInclude.Include.NON_NULL)
class SwaggerUiConfiguration(
	// Core
	val configUrl: String? = null,
	val spec: Map<String, String>? = null,
	url: JsOrString = """window.location.origin + '/openapi'""",
	val urls: List<String>? = null,

	// Display
	val deepLinking: Boolean? = null,
	val displayOperationId: Boolean? = null,
	val defaultModelsExpandDepth: Int? = null,
	val defaultModelExpandDepth: Int? = null,
	val defaultModelRendering: String? = null,
	val displayRequestDuration: Boolean? = null,
	val docExpansion: String? = null,
	val filter: Boolean? = null,
	val maxDisplayedTags: Int? = null,
	@field:JsonRawValue
	val operationsSorter: JsFunction? = null,
	@field:JsonRawValue
	val tagsSorter: JsFunction? = null,
	val showExtensions: Boolean? = null,
	val showCommonExtensions: Boolean? = null,
	val useUnsafeMarkdown: Boolean? = null,
	val syntaxHighlight: SyntaxHighlight? = null,

	// Network
	val oauth2RedirectUrl: String? = null,
	@field:JsonRawValue
	val requestInterceptor: JsFunction? = null,
//	@field:JsonProperty("request.curlOptions")
//	@field:JsonRawValue
//	val curlOptions: JsOrString = JS_UNDEFINED,
	@field:JsonRawValue
	val responseInterceptor: JsFunction? = null,
	val showMutatedRequest: Boolean? = null,
	val supportedSubmitMethods: List<String>? = null,
	val validatorUrl: String? = null,
	val withCredentials: Boolean? = null,

	// Macros
	@field:JsonRawValue
	val modelPropertyMacro: JsFunction? = null,
	@field:JsonRawValue
	val parameterMacro: JsFunction? = null,

	// Authorization
	val persistAuthorization: Boolean? = null,

	//Plugin system
	val layout: String = "StandaloneLayout",
	@field:JsonRawValue
	val presets: JsArrayOfObjects = """[
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ]""",
	@field:JsonRawValue
	val plugins: JsArrayOfObjects? = null
//	= """[
//          SwaggerUIBundle.plugins.DownloadUrl
//        ]"""
) {
	@field:JsonProperty("dom_id")
	private val domId: String = "#swagger-ui"

	@field:JsonRawValue
	val url = if (url.contains("""["']""".toRegex())) {
		//probably raw js. use as is.
		url
	}
	// otherwise probably a path/url. wrap in quotes.
	else "'$url'"
}

private const val JS_UNDEFINED = "undefined"

// JavaScript concatenated string (window.location.origin + '/something') or plain string
typealias JsOrString = String
// function() {} or () => {}
typealias JsFunction = String
// [{}, GlobalObject...]
typealias JsArrayOfObjects = String
// any dom element to attach the ui to
typealias JsElementRef = String
