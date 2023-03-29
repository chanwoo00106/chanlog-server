package dev.chanlog.chanlogserver.domain.blog.repository

import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import org.springframework.data.repository.CrudRepository

interface BlogRepository: CrudRepository<Blog, String> {
  override fun findAll(): List<Blog>
}