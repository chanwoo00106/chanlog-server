package dev.chanlog.chanlogserver.global.exception

data class BasicException(val errorCode: ErrorCode): RuntimeException() {
  override val message: String = errorCode.message
  val status: Int = errorCode.status
}