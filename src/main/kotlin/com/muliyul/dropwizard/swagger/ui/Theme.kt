package com.muliyul.dropwizard.swagger.ui

import com.fasterxml.jackson.annotation.*

@Suppress("unused")
enum class Theme {
	Agate,
	Arta,
	Monokai,
	Nord,
	Obsidian,
	Tomorrow_Night;

	@JsonValue
	private fun toJson() = this.name.toLowerCase().replace('_', '-')
}
