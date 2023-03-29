package dev.chanlog.chanlogserver.domain.auth.service

import dev.chanlog.chanlogserver.domain.auth.dto.request.ReissueTokenRequestDto
import dev.chanlog.chanlogserver.domain.auth.dto.response.CookiesResponseDto

interface ReissueTokenService {
  fun execute(reissueTokenDto: ReissueTokenRequestDto): CookiesResponseDto
}