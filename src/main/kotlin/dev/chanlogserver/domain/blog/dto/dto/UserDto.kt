package dev.chanlogserver.domain.blog.dto.dto

import dev.chanlogserver.domain.user.User

class UserDto(
  private val user: User
) {
  val email = user.email
  val name = user.name
}