package dev.chanlog.chanlogserver.global.security.user

import dev.chanlog.chanlogserver.domain.user.entity.User
import dev.chanlog.chanlogserver.global.security.jwt.JwtProvider
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
  private val user: User,
  private val jwtProvider: JwtProvider
): UserDetails {
  override fun getAuthorities(): MutableCollection<out GrantedAuthority>
    = mutableListOf(user.role)

  override fun getPassword(): String = user.password

  override fun getUsername(): String = user.id

  // 토큰이 만료됐는지 확인
  override fun isAccountNonExpired(): Boolean = true

  // 계정이 잠겨있는지 여부를 반환
  override fun isAccountNonLocked(): Boolean = true

  // 자격증명이 만료되었는 지를 반환
  override fun isCredentialsNonExpired(): Boolean = true

  // 계정이 활성화 되어있는지 여부를 반환
  override fun isEnabled(): Boolean = true
}