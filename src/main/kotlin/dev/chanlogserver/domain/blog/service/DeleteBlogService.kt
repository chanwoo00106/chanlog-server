package dev.chanlogserver.domain.blog.service

import dev.chanlogserver.domain.blog.dto.request.DeleteBlogRequestDto

interface DeleteBlogService {
  fun execute(data: DeleteBlogRequestDto)
}