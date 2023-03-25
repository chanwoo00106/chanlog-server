package dev.chanlog.chanlogserver.global.security.auth.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class RefreshAuthentication(
  private val token: String
): Authentication {
  override fun getName(): String = token

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

  override fun getCredentials(): Any = ""

  override fun getDetails(): Any = "refresh"

  override fun getPrincipal(): Any = ""

  override fun isAuthenticated(): Boolean = true

  override fun setAuthenticated(isAuthenticated: Boolean) {}
}