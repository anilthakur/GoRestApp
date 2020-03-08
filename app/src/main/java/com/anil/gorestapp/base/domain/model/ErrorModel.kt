package com.anil.gorestapp.base.domain.model

import com.anil.gorestapp.base.domain.ResponseServiceCode


/**
 * Default error model that comes from server if something goes wrong with a repository call
 */
data class ErrorModel(
        val key: String = "",
        val message: String = "",
        val code: Int = -1,
        var responseCode: ResponseServiceCode? = null,
        var isServiceError: Boolean = false
)