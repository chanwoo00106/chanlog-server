package dev.chanlog.chanlogserver.global.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import dev.chanlog.chanlogserver.global.exception.ErrorResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.Exception

class ExceptionFilter(
  private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    try {
      filterChain.doFilter(request, response)
    } catch (e: BasicException) {
      settingErrorResponse(response, e)
    } catch (e: Exception) {
      settingErrorResponse(response, BasicException(ErrorCode.INTERNAL_ERROR))
    }
  }

  private fun settingErrorResponse(response: HttpServletResponse, e: BasicException) {
    val errorResponse = objectMapper.writeValueAsString(ErrorResponse(e.status, e.message))
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    response.characterEncoding = "UTF-8"
    response.writer.write(errorResponse)
    response.status = e.status
  }
}