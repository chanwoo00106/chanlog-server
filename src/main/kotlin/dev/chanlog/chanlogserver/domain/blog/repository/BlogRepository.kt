package dev.chanlog.chanlogserver.domain.blog.repository

import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository: JpaRepository<Blog, String> {
  fun findAllByTitle(page: Pageable, title: String?): Page<Blog>
}