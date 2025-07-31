package com.omnistore.omnistore_user_service.infrastructure.exception

import com.omnistore.omnistore_user_service.domain.exception.EmailAlreadyExistsException
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException::class)
    fun handleInputErrors(ex: EmailAlreadyExistsException, request: HttpServletRequest): ErrorResponseDto {
        return ErrorResponseDto(
            status = 400,
            message = ex.message,
            path = request.requestURI,
            timestamp = System.currentTimeMillis().toString()
        )
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleNotFoundErrors(ex: UserNotFoundException, request: HttpServletRequest): ErrorResponseDto {
        return ErrorResponseDto(
            status = 404,
            message = ex.message,
            path = request.requestURI,
            timestamp = System.currentTimeMillis().toString()
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponseDto> {
        val errors = ex.bindingResult.allErrors.joinToString("; ") { error ->
            if (error is FieldError) {
                "${error.field}: ${error.defaultMessage}"
            } else {
                error.defaultMessage ?: "Validation error"
            }
        }

        val errorResponse = ErrorResponseDto(
            status = 400,
            message = errors,
            path = request.requestURI,
            timestamp = System.currentTimeMillis().toString()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        ex: MethodArgumentTypeMismatchException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponseDto> {
        val message = "Parameter '${ex.name}' must be of type '${ex.requiredType?.simpleName}', but received: '${ex.value}'"

        val errorResponse = ErrorResponseDto(
            status = 400,
            message = message,
            path = request.requestURI,
            timestamp = System.currentTimeMillis().toString()
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponseDto> {
        val errorResponse = ErrorResponseDto(
            status = 500,
            message = ex.message ?: "Unknown error",
            path = request.requestURI,
            timestamp = System.currentTimeMillis().toString()
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}
