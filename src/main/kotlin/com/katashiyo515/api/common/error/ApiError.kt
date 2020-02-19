package com.katashiyo515.api.common.error

import java.io.Serializable

class ApiError : Serializable {
    private val serialVersionUID = 1L

    var error : ErrorDetail = ErrorDetail()

    inner class ErrorDetail {
        var code: String? = null
        var message: String? = null
        var status: String? = null
    }
}