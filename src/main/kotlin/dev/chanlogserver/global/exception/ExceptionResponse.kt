package dev.chanlogserver.global.exception

import java.util.Date

data class ExceptionResponse(
  val status: Int,
  val path: String,
  val message: String?
) {
  val time = Date()
}