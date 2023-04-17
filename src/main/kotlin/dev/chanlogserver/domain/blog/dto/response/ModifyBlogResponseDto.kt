package dev.chanlogserver.domain.blog.dto.response

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.dto.ImageDto

data class ModifyBlogResponseDto (
  private val blog: Blog
) {
  val title = blog.title
  val thumbnail = blog.thumbnail
  val content = blog.content.content
  val createAt = blog.createdAt
  val updatedAt = blog.updatedAt
  val images = blog.images.map { i -> ImageDto(i.url) }
}
