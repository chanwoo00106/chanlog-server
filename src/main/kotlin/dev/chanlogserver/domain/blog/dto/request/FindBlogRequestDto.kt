package dev.chanlogserver.domain.blog.dto.request

data class FindBlogRequestDto(
  val title: String?,
  val page: Int?,
  val email: String?
)
