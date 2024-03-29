package dev.chanlogserver.global.security.auth.manage

import dev.chanlogserver.global.security.auth.provider.AccessAuthenticationProvider
import dev.chanlogserver.global.security.auth.provider.RefreshAuthenticationProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AuthenticationManager(
  private val accessAuthenticationProvider: AccessAuthenticationProvider,
  private val refreshAuthenticationProvider: RefreshAuthenticationProvider
): AuthenticationManager {
  override fun authenticate(authentication: Authentication): Authentication? {
    if (accessAuthenticationProvider.supports(authentication::class.java))
      return accessAuthenticationProvider.authenticate(authentication)
    else if (refreshAuthenticationProvider.supports(authentication::class.java))
      return refreshAuthenticationProvider.authenticate(authentication)

    return null
  }
}