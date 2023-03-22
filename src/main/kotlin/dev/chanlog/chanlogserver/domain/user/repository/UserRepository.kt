package dev.chanlog.chanlogserver.domain.user.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.userdetails.User
import java.util.*

interface UserRepository: CrudRepository<User, String> {
  override fun findById(id: String): Optional<User>
}