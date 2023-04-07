package dev.chanlogserver.domain.blog.dto.dto

import jakarta.validation.constraints.Pattern

data class ImageDto (
  @field:Pattern(regexp = "https?://(www.)?[-a-zA-Z0-9@:%._+~#=]{2,256}.[-a-zA-Z0-9@:%._+~#=]{2,256}", message = "url 형식으로 맞춰주세요")
  val image: String
)