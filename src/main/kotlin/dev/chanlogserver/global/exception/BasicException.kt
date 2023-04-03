package dev.chanlogserver.global.exception

class BasicException(errorCode: ErrorCode): RuntimeException() {
  val status = errorCode.status
  override val message = errorCode.message
}