package dev.chanlogserver.domain.user

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
  ROLE_USER, ROLE_ADMIN;

  override fun getAuthority(): String = name

  companion object {
    fun valueOf(value: Any?): Role? {
      if (!arrayListOf(ROLE_USER.name, ROLE_ADMIN.name).contains(value))
        return null

      return Role.valueOf(value.toString())
    }
  }
}