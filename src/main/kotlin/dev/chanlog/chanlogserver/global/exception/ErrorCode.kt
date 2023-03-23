package dev.chanlog.chanlogserver.global.exception

enum class ErrorCode (val status: Int, val message: String) {
  EXPIRED_TOKEN(403, "토큰 값이 만료되었습니다"),
  INVALID_TOKEN(403, "유효하지 않은 토큰"),
  FAILED_TOKEN(401, "로그인에 실패했습니다");

}