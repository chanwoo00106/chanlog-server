package dev.chanlogserver.domain.blog.dto.response

import dev.chanlogserver.domain.blog.dto.dto.ImageDto
import dev.chanlogserver.domain.image.Image
import java.time.LocalDateTime

data class CreateResponseDto (
  val title: String,
  val thumbnail: String,
  val content: String,
  val createAt: LocalDateTime,
  private val image: MutableIterable<Image>
) {
  val images = image.map { i -> ImageDto(i.url) }
}