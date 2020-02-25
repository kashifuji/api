package com.katashiyo515.api.common.exception

import java.lang.RuntimeException

abstract class ApiRuntimeException( msg: String = "") : RuntimeException(msg) {
    abstract var detail: String
    abstract var code: String
}