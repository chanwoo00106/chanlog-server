package dev.chanlog.chanlogserver.global.security.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(BasicException::class)
  fun globalExceptionHandler(request: HttpServletRequest, error: BasicException): ResponseEntity<ErrorResponse> {
    val errorCode = error.errorCode
    return ResponseEntity(ErrorResponse(errorCode.status, errorCode.message), HttpStatus.valueOf(errorCode.status))
  }
}