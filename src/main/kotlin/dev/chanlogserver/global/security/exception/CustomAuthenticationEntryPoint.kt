package dev.chanlogserver.global.security.exception

import com.fasterxml.jackson.databind.ObjectMapper
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.exception.ExceptionResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint(
  private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {
  override fun commence(
    request: HttpServletRequest,
    response: HttpServletResponse,
    authException: AuthenticationException
  ) {
    val errorCode = ErrorCode.UNAUTHORIZED
    val resBody = objectMapper.writeValueAsString(ExceptionResponse(errorCode.status, request.requestURI, errorCode.message))
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.characterEncoding = "UTF-8"
    response.status = errorCode.status
    response.writer.write(resBody)
  }
}