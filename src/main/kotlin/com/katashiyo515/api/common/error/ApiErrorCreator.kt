package com.katashiyo515.api.common.error

import com.katashiyo515.api.common.message.Message
import org.springframework.stereotype.Component

@Component
class ApiErrorCreator {
    fun createApiError(apiErrorDefinition: ApiErrorDefinition, vararg args: Any): ApiError {
        var apiError = ApiError()
        apiError.error.code = apiErrorDefinition.code
        apiError.error.message = Message.getMessage(apiErrorDefinition.messageCode, *args)
        apiError.error.status = apiErrorDefinition.httpStatus.value().toString()
        return apiError
    }
}
