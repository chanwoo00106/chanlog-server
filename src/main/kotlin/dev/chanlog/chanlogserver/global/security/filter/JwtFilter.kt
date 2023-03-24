package dev.chanlog.chanlogserver.global.security.filter

import dev.chanlog.chanlogserver.global.security.auth.authentication.AccessAuthentication
import dev.chanlog.chanlogserver.global.security.auth.authentication.RefreshAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
  private val authenticationManager: AuthenticationManager
): OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    var authentication: Authentication? =  null

    if (request.cookies == null)
      return filterChain.doFilter(request, response)

    val accessCookie = request.cookies.find { cookie -> cookie.name == "accessToken" }
    if (accessCookie != null)
      authentication = authenticationManager.authenticate(AccessAuthentication(accessCookie.value))

    val refreshCookie = request.cookies.find { cookie -> cookie.name == "refreshToken" }
    if (authentication != null && refreshCookie != null)
      authentication = authenticationManager.authenticate(RefreshAuthentication(refreshCookie.value))

    if (authentication != null) SecurityContextHolder.getContext().authentication = authentication

    filterChain.doFilter(request, response)
  }
}