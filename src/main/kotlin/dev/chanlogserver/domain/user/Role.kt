package dev.chanlogserver.domain.user

import jakarta.persistence.Enumerated
import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
  ROLE_USER, ROLE_ADMIN;

  override fun getAuthority(): String = name
}