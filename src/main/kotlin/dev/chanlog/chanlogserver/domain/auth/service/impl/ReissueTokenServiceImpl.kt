package dev.chanlog.chanlogserver.domain.auth.service.impl

import dev.chanlog.chanlogserver.domain.auth.dto.request.ReissueTokenRequestDto
import dev.chanlog.chanlogserver.domain.auth.dto.response.CookiesResponseDto
import dev.chanlog.chanlogserver.domain.auth.service.ReissueTokenService
import dev.chanlog.chanlogserver.domain.user.entity.User
import dev.chanlog.chanlogserver.domain.user.repository.UserRepository
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class ReissueTokenServiceImpl(
  private val userRepository: UserRepository,
  private val signinServiceImpl: SigninServiceImpl
): ReissueTokenService {
  override fun execute(reissueTokenDto: ReissueTokenRequestDto): CookiesResponseDto {
    val user = checkRefreshToken(reissueTokenDto)

    val tokens = signinServiceImpl.createJwtToken(user)

    signinServiceImpl.saveRefresh(tokens.refreshJwt, user)

    val accessToken = signinServiceImpl.createCookie("accessToken", tokens.accessJwt)
    val refreshToken = signinServiceImpl.createCookie("refreshToken", tokens.refreshJwt)

    return CookiesResponseDto(accessToken, refreshToken)
  }

  private fun checkRefreshToken(reissueTokenDto: ReissueTokenRequestDto): User {
    val user = userRepository.findById(reissueTokenDto.authentication.name)
      .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

    if (user.refreshToken?.token != reissueTokenDto.refreshToken)
      throw BasicException(ErrorCode.EXPIRED_TOKEN)

    return user
  }
}