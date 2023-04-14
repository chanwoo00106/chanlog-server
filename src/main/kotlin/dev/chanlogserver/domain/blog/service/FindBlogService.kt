package dev.chanlogserver.domain.blog.service

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.request.FindBlogRequestDto
import dev.chanlogserver.domain.blog.dto.response.FindBlogResponseDto

interface FindBlogService {
  fun execute(data: FindBlogRequestDto): List<FindBlogResponseDto>
}