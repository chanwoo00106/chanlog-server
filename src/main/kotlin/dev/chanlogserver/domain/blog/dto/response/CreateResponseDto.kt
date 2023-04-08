package dev.chanlogserver.domain.blog.dto.response

import java.time.LocalTime

data class CreateResponseDto (
  val title: String,
  val thumbnail: String,
  val content: String,
  val createAt: LocalTime
)