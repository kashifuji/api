package com.katashiyo515.api.common.error

import org.springframework.http.HttpStatus

enum class ApiErrorDefinition(val httpStatus: HttpStatus, val messageCode: String, val code: String) {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"e.api.internal_server_error", "internal_server_error"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"e.api.not_found", "not_found"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST ,"e.api.bad_request", "bad_request"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,"e.api.resource_not_found", "resource_not_found"),
    RESOURCE_ALREDY_EXISTS(HttpStatus.CONFLICT,"e.api.resource_already_exists", "resource_already_exists"),
    ABORTED(HttpStatus.CONFLICT,"e.api.aborted", "aborted");
}