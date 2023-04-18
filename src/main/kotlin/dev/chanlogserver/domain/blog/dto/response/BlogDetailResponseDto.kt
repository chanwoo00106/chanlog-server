package dev.chanlogserver.domain.blog.dto.response

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.dto.ImageDto
import dev.chanlogserver.domain.blog.dto.dto.UserDto

data class BlogDetailResponseDto (
  val blog: Blog
) {
  val title = blog.title
  val content = blog.content.content
  val createdAt = blog.createdAt
  val updatedAt = blog.updatedAt
  val thumbnail = blog.thumbnail
  val images = blog.images.map { image -> ImageDto(image.url) }
  val user = UserDto(blog.user)
}