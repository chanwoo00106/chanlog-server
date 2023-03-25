package dev.chanlog.chanlogserver.global.security.filter

import dev.chanlog.chanlogserver.global.security.auth.authentication.AccessAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class AccessFilter(
  private val authenticationManager: AuthenticationManager
): OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    if (request.cookies.isNullOrEmpty())
      return filterChain.doFilter(request, response)

    val accessCookie = request.cookies.find { cookie -> cookie.name == "accessToken" }
    if (accessCookie?.value?.isNotBlank() == true) {
      val authentication = authenticationManager.authenticate(AccessAuthentication(accessCookie.value))
      SecurityContextHolder.getContext().authentication = authentication
    }

    filterChain.doFilter(request, response)
  }

  override fun shouldNotFilter(request: HttpServletRequest): Boolean {
    val isLoginQuery = request.requestURI == "/auth" && request.method == "POST"
    val isRefreshQuery = request.requestURI == "/auth" && request.method == "PATCH"

    return isLoginQuery || isRefreshQuery
  }
}