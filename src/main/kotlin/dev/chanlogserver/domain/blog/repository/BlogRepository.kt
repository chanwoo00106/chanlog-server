package dev.chanlogserver.domain.blog.repository

import dev.chanlogserver.domain.blog.Blog
import org.springframework.data.repository.CrudRepository

interface BlogRepository: CrudRepository<Blog, String> {
}