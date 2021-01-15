package com.muliyul.dropwizard.swagger.integration

import com.muliyul.dropwizard.swagger.ext.*
import org.openqa.selenium.remote.*
import org.openqa.selenium.support.*
import org.openqa.selenium.support.ui.*

import org.openqa.selenium.support.FindBy
import org.openqa.selenium.WebElement


class SwaggerUiPage(
	private val webDriver: RemoteWebDriver
) : LoadableComponent<SwaggerUiPage>() {
	@FindBy(id = "operations-default-sanity")
	private lateinit var sanityResource: WebElement

	init {
		PageFactory.initElements(webDriver, this)
		get()
	}

	override fun load() {
		webDriver.relativeGet("/swagger")
	}

	override fun isLoaded() {
		webDriver.wait(5).untilError { sanityResource.isDisplayed }
	}

	fun expandSanity() {
		sanityResource.click()
	}
}
