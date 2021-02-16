package com.muliyul.dropwizard.swagger.ext

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.*

fun WebDriver.wait(timeoutInSeconds: Long) = WebDriverWait(this, timeoutInSeconds)

fun <T> WebDriverWait.untilError(block: (WebDriver) -> T): T = try {
	until { block(it) }
} catch (e: Throwable) {
	throw Error(e)
}

val WebDriver.origin
	get() = currentUrl.substring(0, currentUrl.indexOf("/", "https://".length))

fun WebDriver.relativeGet(path: String) = get(currentUrl + path.removePrefix("/"))
