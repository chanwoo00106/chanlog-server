package dev.chanlogserver.global.security.auth.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class RefreshAuthentication(
  private val token: String
): Authentication {
  override fun getName(): String = token

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

  override fun getCredentials() = null

  override fun getDetails() = null

  override fun getPrincipal() = null

  override fun isAuthenticated() = true

  override fun setAuthenticated(isAuthenticated: Boolean) {}
}