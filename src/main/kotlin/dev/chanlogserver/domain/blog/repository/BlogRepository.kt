package dev.chanlogserver.domain.blog.repository

import dev.chanlogserver.domain.blog.Blog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BlogRepository: JpaRepository<Blog, String> {
  fun findAllByTitleAndUser(page: Pageable, title: String, email: String): Page<Blog>
}