package dev.chanlog.chanlogserver.domain.user

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
  ROLE_ADMIN, ROLE_USER;
  override fun getAuthority(): String = name
}
