package com.katashiyo515.api.common.error

enum class ErrorCodeDefinition(val code: String) {
    UnKnown("e.sampleapi.unknown"),
    NOT_FOUND("e.sampleapi.application.not_found"),
    BAD_REQUEST("e.sampleapi.application.bad_request"),
    ResourceNotFound("e.sampleapi.repository.resource_not_found"),
    ResourceAlreadyExists("e.sampleapi.repository.resource_already_exists"),
    ABORTED("e.sampleapi.repository.aborted"),
    KonamonResourceConnectionFailed("e.sampleapi.repository.konamon.connection_failed");
}