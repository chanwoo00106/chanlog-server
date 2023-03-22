package dev.chanlog.chanlogserver.domain.user

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
  ADMIN_ROLE, USER_ROLE;
  override fun getAuthority(): String = name
}
