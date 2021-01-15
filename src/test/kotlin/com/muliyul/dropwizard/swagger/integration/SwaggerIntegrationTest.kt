package com.muliyul.dropwizard.swagger.integration

import com.muliyul.dropwizard.swagger.*
import io.dropwizard.testing.*
import io.dropwizard.testing.junit5.*
import io.github.bonigarcia.seljup.*
import org.glassfish.jersey.client.proxy.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.junit.jupiter.api.extension.Extensions
import org.openqa.selenium.chrome.*
import kotlin.test.*
import kotlin.test.Test

@Extensions(
	ExtendWith(DropwizardExtensionsSupport::class),
	ExtendWith(SeleniumJupiter::class)
)
@SingleSession
class SwaggerIntegrationTest(
	@DockerBrowser(type = BrowserType.CHROME) private val webDriver: ChromeDriver
) {
	companion object {
		@JvmStatic
		private val ext = DropwizardAppExtension(
			TestSwaggerApplication::class.java,
			"config-test.yaml",
			ConfigOverride.randomPorts()
		)
	}

	@BeforeEach
	fun beforeEach() {
		webDriver.get("http://localhost:${ext.localPort}")
	}

	@Test
	fun `should expose default endpoint`() {
		SwaggerUiPage(webDriver)
			.expandSanity()

		println()
	}
}
