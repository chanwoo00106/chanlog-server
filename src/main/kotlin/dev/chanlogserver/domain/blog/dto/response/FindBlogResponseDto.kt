package dev.chanlogserver.domain.blog.dto.response

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.dto.ImageDto
import dev.chanlogserver.domain.blog.dto.dto.UserDto
import org.springframework.data.domain.Page

data class FindBlogResponseDto(
  private val blog: Blog
) {
  val title = blog.title
  val content = blog.content.content
  val thumbnail = blog.thumbnail
  val createdAt = blog.createdAt
  val updatedAt = blog.updatedAt
  val user = UserDto(blog.user)
  val image = blog.images.map { i -> ImageDto(i.url) }
}