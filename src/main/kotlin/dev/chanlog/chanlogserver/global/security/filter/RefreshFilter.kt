package dev.chanlog.chanlogserver.global.security.filter

import dev.chanlog.chanlogserver.global.security.auth.authentication.RefreshAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class RefreshFilter(
  private val authenticationManager: AuthenticationManager
): OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    if (request.cookies.isNullOrEmpty())
      return filterChain.doFilter(request, response)

    val refreshCookie = request.cookies.find { cookie -> cookie.name == "refreshToken" }
    if (refreshCookie?.value?.isNotBlank() == true) {
      val authentication = authenticationManager.authenticate(RefreshAuthentication(refreshCookie.value))
      SecurityContextHolder.getContext().authentication = authentication
    }

    filterChain.doFilter(request, response)
  }

  override fun shouldNotFilter(request: HttpServletRequest): Boolean {
    val isRefreshQuery = request.requestURI == "/auth" && request.method == "PATCH"

    return !isRefreshQuery
  }
}