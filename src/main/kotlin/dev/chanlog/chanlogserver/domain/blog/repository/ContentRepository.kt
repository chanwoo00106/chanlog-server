package dev.chanlog.chanlogserver.domain.blog.repository

import dev.chanlog.chanlogserver.domain.blog.entity.Content
import org.springframework.data.repository.CrudRepository

interface ContentRepository: CrudRepository<Content, Long> {
}