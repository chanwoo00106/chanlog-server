package dev.chanlog.chanlogserver.global.security.auth.user

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails

interface UserDetailsService {
  fun loadUserByUsername(claims: Claims): UserDetails
}