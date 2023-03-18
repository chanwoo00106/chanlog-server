package dev.chanlog.chanlogserver.domain.user

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
  ADMIN, USER;
  override fun getAuthority(): String = name
}
