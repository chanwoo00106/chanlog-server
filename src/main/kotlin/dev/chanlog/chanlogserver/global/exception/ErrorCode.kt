package dev.chanlog.chanlogserver.global.exception

enum class ErrorCode (val status: Int, val message: String) {
  FAILED_TOKEN(401, "로그인에 실패했습니다"),
  EXPIRED_TOKEN(403, "토큰 값이 만료되었습니다"),
  INVALID_TOKEN(403, "유효하지 않은 토큰"),

  INTERNAL_ERROR(500, "서버 내부에 문제가 생겼습니다.\n잠시 후에 다시 이용해 주세요");
}