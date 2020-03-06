package com.katashiyo515.api.common.exception

import com.katashiyo515.api.common.error.ErrorCodeDefinition
import com.katashiyo515.api.common.message.Message

class ResourceNotFoundException(msg: String = "", val id: Int? = null) : ApiRuntimeException(msg) {
    override var detail: String = ""
        get() {
            return when (id) {
                null -> Message.getMessage(code, "すべて")
                else -> Message.getMessage(code, "id = $id")
            }
        }
    override var code: String = ErrorCodeDefinition.ResourceNotFound.code
}
