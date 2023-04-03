package dev.chanlogserver.domain.test.dto.request

import jakarta.validation.constraints.NotBlank

data class SayHelloRequest (
  @field:NotBlank(message = "not null")
  val test: String
)