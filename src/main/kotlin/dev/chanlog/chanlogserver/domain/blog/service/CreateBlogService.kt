package dev.chanlog.chanlogserver.domain.blog.service

import dev.chanlog.chanlogserver.domain.blog.dto.request.CreateBlogRequestDto
import dev.chanlog.chanlogserver.domain.blog.dto.response.CreateBlogResponseDto
import org.springframework.security.core.Authentication

interface CreateBlogService {
  fun execute (data: CreateBlogRequestDto, authentication: Authentication): CreateBlogResponseDto
}