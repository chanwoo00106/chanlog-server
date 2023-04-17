package dev.chanlogserver.global.exception

enum class ErrorCode(val status: Int, val message: String) {
  FAILED_LOGIN(400, "로그인에 실패했습니다"),
  NOT_FOUND_BLOG(400, "블로그를 찾을 수 없습니다"),
  INVALID_TOKEN(401, "인증되지 않은 토큰입니다"),
  UNAUTHORIZED(401, "인증에 실패했습니다"),
  EXPIRED_TOKEN(401, "토큰이 만료되었습니다"),
  FORBIDDEN(403, "접근할 수 없습니다"),
  DUPLICATE_BLOG_TITLE(409, "같은 이름의 블로그가 이미 존재합니다"),
  INTERNAL_ERROR(500, "알 수 없는 에러가 발생했습니다.\n관리자에게 문의해 주세요")
}