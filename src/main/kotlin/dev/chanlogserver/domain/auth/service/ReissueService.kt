package dev.chanlogserver.domain.auth.service

import dev.chanlogserver.domain.auth.dto.request.ReissueRequestDto
import dev.chanlogserver.domain.auth.dto.response.CookieResponseDto

interface ReissueService {
  fun execute(data: ReissueRequestDto): CookieResponseDto
}