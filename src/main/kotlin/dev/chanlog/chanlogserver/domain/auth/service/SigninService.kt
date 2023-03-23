package dev.chanlog.chanlogserver.domain.auth.service

import dev.chanlog.chanlogserver.domain.auth.dto.request.SigninRequestDto
import dev.chanlog.chanlogserver.domain.auth.dto.response.SigninResponseDto

interface SigninService {
  fun execute(data: SigninRequestDto): SigninResponseDto
}