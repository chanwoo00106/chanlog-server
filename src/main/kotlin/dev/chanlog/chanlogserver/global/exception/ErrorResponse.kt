package dev.chanlog.chanlogserver.global.exception

data class ErrorResponse (
  val status: Int,
  val message: String
)