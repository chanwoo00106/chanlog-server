package dev.chanlogserver.domain.blog.service

import dev.chanlogserver.domain.blog.dto.request.BlogDetailRequestDto
import dev.chanlogserver.domain.blog.dto.response.BlogDetailResponseDto

interface BlogDetailDetailService {
  fun execute(data: BlogDetailRequestDto): BlogDetailResponseDto
}