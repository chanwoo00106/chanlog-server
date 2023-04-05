package dev.chanlogserver.domain.user.repository

import dev.chanlogserver.domain.user.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
}