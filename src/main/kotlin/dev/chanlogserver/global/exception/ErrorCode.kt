package dev.chanlogserver.global.exception

enum class ErrorCode(val status: Int, val message: String) {
  INVALID_TOKEN(401, "인증되지 않은 토큰입니다"),
  UNAUTHORIZED(401, "인증에 실패했습니다"),
  EXPIRED_TOKEN(401, "토큰이 만료되었습니다"),
  FORBIDDEN(403, "접근할 수 없습니다"),
  INTERNAL_ERROR(500, "알 수 없는 에러가 발생했습니다.\n관리자에게 문의해 주세요")
}