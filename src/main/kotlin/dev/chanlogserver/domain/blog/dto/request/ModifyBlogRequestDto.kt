package dev.chanlogserver.domain.blog.dto.request

import dev.chanlogserver.domain.blog.dto.dto.ImageDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ModifyBlogRequestDto (
  @field:NotBlank(message = "썸네일은 필수값입니다")
  @field:Pattern(regexp = "https?://(www.)?[-a-zA-Z0-9@:%._+~#=]{2,256}.[-a-zA-Z0-9@:%._+~#=]{2,256}", message = "url 형식으로 맞춰주세요")
  val thumbnail: String,

  @field:NotBlank(message = "내용은 필수값입니다")
  val content: String,

  @field:Valid
  val images: List<ImageDto>
)