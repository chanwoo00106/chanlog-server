package dev.chanlog.chanlogserver.domain.auth.repository

import dev.chanlog.chanlogserver.domain.auth.entity.Refresh
import org.springframework.data.repository.CrudRepository

interface RefreshRepository: CrudRepository<Refresh, Long>