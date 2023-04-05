package dev.chanlogserver.domain.auth.service

import dev.chanlogserver.domain.auth.dto.request.LoginRequestDto
import dev.chanlogserver.domain.auth.dto.response.LoginResponseDto

interface LoginService {
  fun execute(body: LoginRequestDto): LoginResponseDto
}