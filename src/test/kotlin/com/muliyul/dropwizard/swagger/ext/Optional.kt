package com.muliyul.dropwizard.swagger.ext

import java.util.*

fun <T> T?.toOptional() = Optional.ofNullable(this)
