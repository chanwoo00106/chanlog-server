package dev.chanlogserver.domain.blog.service

import dev.chanlogserver.domain.blog.dto.request.CreateRequestDto
import dev.chanlogserver.domain.blog.dto.response.CreateResponseDto

interface CreateBlogService {
  fun execute(email: String, data: CreateRequestDto): CreateResponseDto
}