package dev.chanlog.chanlogserver.domain.user.repository

import dev.chanlog.chanlogserver.domain.user.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String>