package com.muliyul.dropwizard.swagger.bundles.auth

import io.dropwizard.auth.*
import java.security.*

abstract class GenericAuthFilterBuilder<C, P : Principal, AF : AbstractAuthFilter<C, P>> :
	AuthFilter.AuthFilterBuilder<C, P, AF>()
