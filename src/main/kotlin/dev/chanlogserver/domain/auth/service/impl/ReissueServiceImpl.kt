package dev.chanlogserver.domain.auth.service.impl

import dev.chanlogserver.domain.auth.dto.request.ReissueRequestDto
import dev.chanlogserver.domain.auth.dto.response.LoginResponseDto
import dev.chanlogserver.domain.auth.service.ReissueService
import dev.chanlogserver.domain.user.User
import dev.chanlogserver.domain.user.repository.UserRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import dev.chanlogserver.global.security.jwt.TokenType
import org.springframework.stereotype.Service

@Service
class ReissueServiceImpl(
  private val userRepository: UserRepository,
  private val loginServiceImpl: LoginServiceImpl,
): ReissueService {
  override fun execute(data: ReissueRequestDto): LoginResponseDto {
    val user = checkRefreshTokenAndFindUser(data)

    val tokens = loginServiceImpl.createTokens(user)

    loginServiceImpl.refreshTokenSave(tokens.refresh.token, user)

    val accessCookie = loginServiceImpl.createCookie(TokenType.ACCESS, tokens.access)
    val refreshCookie = loginServiceImpl.createCookie(TokenType.REFRESH, tokens.refresh)

    return LoginResponseDto(accessCookie, refreshCookie)
  }

  fun checkRefreshTokenAndFindUser(data: ReissueRequestDto): User {
    val user = userRepository.findById(data.authentication.name)
      .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

    if (user.refresh.token != data.refreshToken)
      throw BasicException(ErrorCode.INVALID_TOKEN)

    return user
  }
}