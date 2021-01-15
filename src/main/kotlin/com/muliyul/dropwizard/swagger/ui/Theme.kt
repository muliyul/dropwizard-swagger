package com.muliyul.dropwizard.swagger.ui

enum class Theme {
	Agate,
	Arta,
	Monokai,
	Nord,
	Obsidian,
	Tomorrow_Night;

	private fun toJson() = this.name.toLowerCase().replace('_', '-')
}
