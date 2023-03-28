package dev.chanlog.chanlogserver.domain.auth.service

import dev.chanlog.chanlogserver.domain.auth.dto.request.ReissueTokenRequestDto

interface ReissueTokenService {
  fun execute(reissueTokenDto: ReissueTokenRequestDto)
}