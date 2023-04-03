package dev.chanlogserver.global.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalException {
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun validationException(
    request: HttpServletRequest,
    exception: MethodArgumentNotValidException
  ): ResponseEntity<ExceptionResponse> {
    val responseBody = ExceptionResponse(HttpStatus.BAD_REQUEST.value(), request.requestURI, exception.bindingResult.allErrors[0].defaultMessage)
    return ResponseEntity(responseBody, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(BasicException::class)
  fun basicException(
    request: HttpServletRequest,
    exception: BasicException
  ): ResponseEntity<ExceptionResponse> {
    val responseBody = ExceptionResponse(exception.status, request.requestURI, exception.message)
    return ResponseEntity(responseBody, HttpStatus.BAD_REQUEST)
  }
}