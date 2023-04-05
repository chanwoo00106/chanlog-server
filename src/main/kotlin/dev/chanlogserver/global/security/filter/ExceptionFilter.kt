package dev.chanlogserver.global.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.exception.ExceptionResponse
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
      sendError(request, response, ErrorCode.INVALID_TOKEN)
    } catch (e: Exception) {
      sendError(request, response, ErrorCode.INTERNAL_ERROR)
    }
  }

  private fun sendError(req: HttpServletRequest, res: HttpServletResponse, errorCode: ErrorCode) {
    val errorResponse = ExceptionResponse(errorCode.status, req.requestURI, errorCode.message)
    val responseString = objectMapper.writeValueAsString(errorResponse)
    res.status = errorCode.status
    res.characterEncoding = "UTF-8"
    res.contentType = MediaType.APPLICATION_JSON_VALUE
    res.writer.write(responseString)
  }
}