package dev.chanlog.chanlogserver.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import dev.chanlog.chanlogserver.global.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint(
  private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {
  override fun commence(
    request: HttpServletRequest,
    response: HttpServletResponse,
    authException: AuthenticationException?
  ) {
    val errorCode = ErrorCode.FORBIDDEN
    val errorBody = objectMapper.writeValueAsString(ErrorResponse(errorCode.status, errorCode.message))

    response.characterEncoding = "UTF-8"
    response.status = errorCode.status
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.writer.write(errorBody)
  }
}