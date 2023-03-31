package dev.chanlog.chanlogserver.domain.blog.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.URL

data class CreateBlogRequestDto (
  @field:NotBlank
  val title: String,

  @field:NotBlank
  @field:URL
  val thumbnail: String,

  @field:NotEmpty
  val content: String
)