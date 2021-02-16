package com.muliyul.dropwizard.swagger.ui.configuration

import com.muliyul.dropwizard.swagger.ui.*

// Mainly for java users
class Builder {
	private var disabled: Boolean = false
	private var configUrl: String? = null
	private var spec: Map<String, String>? = null
	private var url: JsStringOrString = """window.location.origin + '/openapi'"""
	private var urls: List<String>? = null

	// Display
	private var deepLinking: Boolean? = null
	private var displayOperationId: Boolean? = null
	private var defaultModelsExpandDepth: Int? = null
	private var defaultModelExpandDepth: Int? = null
	private var defaultModelRendering: String? = null
	private var displayRequestDuration: Boolean? = null
	private var docExpansion: String? = null
	private var filter: Boolean? = null
	private var maxDisplayedTags: Int? = null
	private var operationsSorter: JsFunction? = null
	private var tagsSorter: JsFunction? = null
	private var showExtensions: Boolean? = null
	private var showCommonExtensions: Boolean? = null
	private var useUnsafeMarkdown: Boolean? = null
	private var syntaxHighlight: SyntaxHighlight? = null

	// Network
	private var oauth2RedirectUrl: String? = null
	private var requestInterceptor: JsFunction? = null

	//	TODO: these break the UI for some reason. not supported in the meantime.
//	@field:JsonProperty("request.curlOptions")
//	@field:JsonRawValue
//	var curlOptions: JsOrString = JS_UNDEFINED,
	private var responseInterceptor: JsFunction? = null
	private var showMutatedRequest: Boolean? = null
	private var supportedSubmitMethods: List<String>? = null
	private var validatorUrl: String? = null
	private var withCredentials: Boolean? = null

	// Macros
	private var modelPropertyMacro: JsFunction? = null
	private var parameterMacro: JsFunction? = null

	// Authorization
	private var persistAuthorization: Boolean? = null

	//Plugin system
	private var layout: String = "StandaloneLayout"
	private var presets: JsArrayOfObjects = """[
          SwaggerUIBundle.presets.apis,
          SwaggerUIStandalonePreset
        ]"""
	private var plugins: JsArrayOfObjects? = null

	fun disabled() = apply {
		this.disabled = true
	}

	fun configUrl(configUrl: String) = apply {
		this.configUrl = configUrl
	}

	fun spec(spec: Map<String, String>) = apply {
		this.spec = spec
	}

	fun url(url: String) = apply {
		this.url = url
	}

	fun urls(vararg urls: String) = apply {
		this.urls = urls.toList()
	}

	fun deepLinking(deepLinking: Boolean) = apply {
		this.deepLinking = deepLinking
	}

	fun displayOperationId(displayOperationId: Boolean) = apply {
		this.displayOperationId = displayOperationId
	}

	fun defaultModelsExpandDepth(defaultModelsExpandDepth: Int) = apply {
		this.defaultModelsExpandDepth = defaultModelsExpandDepth
	}

	fun defaultModelExpandDepth(defaultModelExpandDepth: Int) = apply {
		this.defaultModelExpandDepth = defaultModelExpandDepth
	}

	fun defaultModelRendering(defaultModelRendering: String) = apply {
		this.defaultModelRendering = defaultModelRendering
	}

	fun displayRequestDuration(displayRequestDuration: Boolean) = apply {
		this.displayRequestDuration = displayRequestDuration
	}

	fun docExpansion(docExpansion: String) = apply {
		this.docExpansion = docExpansion
	}

	fun filter(filter: Boolean) = apply {
		this.filter = filter
	}

	fun maxDisplayedTags(maxDisplayedTags: Int) = apply {
		this.maxDisplayedTags = maxDisplayedTags
	}

	fun operationsSorter(operationsSorter: JsFunction) = apply {
		this.operationsSorter = operationsSorter
	}

	fun tagsSorter(tagsSorter: JsFunction) = apply {
		this.tagsSorter = tagsSorter
	}

	fun showExtensions(showExtensions: Boolean) = apply {
		this.showExtensions = showExtensions
	}

	fun showCommonExtensions(showCommonExtensions: Boolean) = apply {
		this.showCommonExtensions = showCommonExtensions
	}

	fun useUnsafeMarkdown(useUnsafeMarkdown: Boolean) = apply {
		this.useUnsafeMarkdown = useUnsafeMarkdown
	}

	fun syntaxHighlight(syntaxHighlight: SyntaxHighlight) = apply {
		this.syntaxHighlight = syntaxHighlight
	}

	fun build() = SwaggerUiConfiguration(
		disabled = disabled,
		configUrl = configUrl,
		spec = spec,
		url = url,
		urls = urls,
		deepLinking = deepLinking,
		displayOperationId = displayOperationId,
		defaultModelsExpandDepth = defaultModelsExpandDepth,
		defaultModelExpandDepth = defaultModelExpandDepth,
		defaultModelRendering = defaultModelRendering,
		displayRequestDuration = displayRequestDuration,
		docExpansion = docExpansion,
		filter = filter,
		maxDisplayedTags = maxDisplayedTags,
		operationsSorter = operationsSorter,
		tagsSorter = tagsSorter,
		showExtensions = showExtensions,
		showCommonExtensions = showCommonExtensions,
		useUnsafeMarkdown = useUnsafeMarkdown,
		syntaxHighlight = syntaxHighlight,
		oauth2RedirectUrl = oauth2RedirectUrl,
		requestInterceptor = requestInterceptor,
		responseInterceptor = responseInterceptor,
		showMutatedRequest = showMutatedRequest,
		supportedSubmitMethods = supportedSubmitMethods,
		validatorUrl = validatorUrl,
		withCredentials = withCredentials,
		modelPropertyMacro = modelPropertyMacro,
		parameterMacro = parameterMacro,
		persistAuthorization = persistAuthorization,
		layout = layout,
		presets = presets,
		plugins = plugins
	)
}
