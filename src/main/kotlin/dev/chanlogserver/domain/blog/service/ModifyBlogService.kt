package dev.chanlogserver.domain.blog.service

import dev.chanlogserver.domain.blog.dto.request.ModifyBlogRequestDto
import dev.chanlogserver.domain.blog.dto.response.ModifyBlogResponseDto

interface ModifyBlogService {
  fun execute(title: String, body: ModifyBlogRequestDto): ModifyBlogResponseDto
}