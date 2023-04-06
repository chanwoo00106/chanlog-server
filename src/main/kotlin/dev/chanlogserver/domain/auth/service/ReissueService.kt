package dev.chanlogserver.domain.auth.service

import dev.chanlogserver.domain.auth.dto.request.ReissueRequestDto
import dev.chanlogserver.domain.auth.dto.response.LoginResponseDto

interface ReissueService {
  fun execute(data: ReissueRequestDto): LoginResponseDto
}