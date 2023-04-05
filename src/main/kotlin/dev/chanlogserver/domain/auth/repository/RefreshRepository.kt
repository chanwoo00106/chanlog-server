package dev.chanlogserver.domain.auth.repository

import dev.chanlogserver.domain.auth.Refresh
import org.springframework.data.repository.CrudRepository

interface RefreshRepository: CrudRepository<Refresh, Long>