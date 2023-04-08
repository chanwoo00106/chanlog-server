package dev.chanlogserver.domain.blog.repository

import dev.chanlogserver.domain.blog.Content
import org.springframework.data.repository.CrudRepository

interface ContentRepository: CrudRepository<Content, Long> {
}