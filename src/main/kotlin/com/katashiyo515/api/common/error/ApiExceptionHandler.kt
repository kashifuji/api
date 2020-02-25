package com.katashiyo515.api.common.error

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.katashiyo515.api.common.exception.ResourceLockedException
import com.katashiyo515.api.common.exception.ResourceDelicateException
import com.katashiyo515.api.common.exception.ResourceNotFoundException
import com.katashiyo515.api.common.message.Message
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.inject.Inject


@ControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {
    lateinit var apiErrorCreator : ApiErrorCreator
        @Inject set

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    //　すべての例外に共通する処理
    override fun handleExceptionInternal(ex: Exception, body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity.status(status).headers(headers).body(
                body ?: apiErrorCreator.createApiError(ApiErrorDefinition.INTERNAL_SERVER_ERROR)
        )
    }

    // 404
    override fun handleNoHandlerFoundException(ex: NoHandlerFoundException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        log.error("[{}] {}", ErrorCodeDefinition.NOT_FOUND.code, Message.getMessage(ErrorCodeDefinition.NOT_FOUND.code), ex)
        return handleExceptionInternal(ex,
                apiErrorCreator.createApiError(ApiErrorDefinition.NOT_FOUND),
                headers, status, request)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val message = when (ex.cause) {
            is InvalidFormatException -> {
                "invalid value. field = " + (ex.cause as InvalidFormatException).path[0].fieldName
            }
            is JsonParseException -> {
                "parse error. near line " + (ex.cause as JsonParseException).processor.currentLocation.lineNr
            }
            is JsonMappingException -> {
                "parse error. near line " + (ex.cause as JsonMappingException).location.lineNr
            }
            else -> "parse error."
        }

        log.error("[{}] {}",ErrorCodeDefinition.BAD_REQUEST, Message.getMessage(ErrorCodeDefinition.BAD_REQUEST.code, message), ex)

        return handleExceptionInternal(ex,
                apiErrorCreator.createApiError(ApiErrorDefinition.BAD_REQUEST, message),
                headers, status, request)
    }

    // request validation
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val message = ex.bindingResult.fieldErrors.joinToString(". ") { it.field + " : " + it.defaultMessage.toString() }
        log.error("[{}] {}",ErrorCodeDefinition.BAD_REQUEST, Message.getMessage(ErrorCodeDefinition.BAD_REQUEST.code, message), ex)
        return handleExceptionInternal(ex,
                apiErrorCreator.createApiError(ApiErrorDefinition.BAD_REQUEST, message),
                headers, status, request)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(e : ResourceNotFoundException, request : WebRequest): ResponseEntity<Any> {
        log.error("[{}] {}", e.code, e.detail, e)
        return handleExceptionInternal(e,
                apiErrorCreator.createApiError(ApiErrorDefinition.RESOURCE_NOT_FOUND, ""),
                HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler(ResourceDelicateException::class)
    fun handleResourceDelicateException(e : ResourceDelicateException, request: WebRequest): ResponseEntity<Any> {
        log.error("[{}] {}", e.code, e.detail, e)
        return handleExceptionInternal(e,
                apiErrorCreator.createApiError(ApiErrorDefinition.RESOURCE_ALREDY_EXISTS, ""),
                HttpHeaders(), HttpStatus.CONFLICT, request)
    }

    @ExceptionHandler(ResourceLockedException::class)
    fun handleResourceLockedExceptionException(e : ResourceLockedException, request: WebRequest): ResponseEntity<Any> {
        log.error("[{}] {}", e.code, e.detail, e)
        return handleExceptionInternal(e,
                apiErrorCreator.createApiError(ApiErrorDefinition.ABORTED),
                HttpHeaders(), HttpStatus.CONFLICT, request)
    }

    // その他の例外
    @ExceptionHandler(Exception::class)
    fun handleAllException(e : Exception, request : WebRequest): ResponseEntity<Any> {
        log.error("[{}] {}", ErrorCodeDefinition.UnKnown.code, Message.getMessage(ErrorCodeDefinition.UnKnown.code), e)
        return handleExceptionInternal(e,
                apiErrorCreator.createApiError(ApiErrorDefinition.INTERNAL_SERVER_ERROR),
                HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request)
    }
}