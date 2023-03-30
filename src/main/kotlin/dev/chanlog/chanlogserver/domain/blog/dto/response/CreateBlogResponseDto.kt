package dev.chanlog.chanlogserver.domain.blog.dto.response

import com.fasterxml.jackson.annotation.JsonIgnore
import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import java.time.LocalDateTime

data class CreateBlogResponseDto (
  @JsonIgnore
  val blog: Blog
) {
  val title: String = blog.title
  val thumbnail: String = blog.thumbnail
  val content: String = blog.content.content
  val createdAt: LocalDateTime = blog.createdAt
}