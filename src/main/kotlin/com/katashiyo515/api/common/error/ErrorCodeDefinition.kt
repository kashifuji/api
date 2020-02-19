package com.katashiyo515.api.common.error

enum class ErrorCodeDefinition(val code: String) {
    BAD_REQUEST("e.sampleapi.application.bad_request"),
    KonamonResourceNotFound("e.sampleapi.repository.konamon.not_found"),
    KonamonResourceConnectionFailed("e.sampleapi.repository.konamon.connection_failed");
}