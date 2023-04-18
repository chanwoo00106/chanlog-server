package dev.chanlogserver.domain.blog.service.impl

import dev.chanlogserver.domain.blog.dto.request.BlogDetailRequestDto
import dev.chanlogserver.domain.blog.dto.response.BlogDetailResponseDto
import dev.chanlogserver.domain.blog.repository.BlogRepository
import dev.chanlogserver.domain.blog.service.BlogDetailDetailService
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class BlogDetailServiceImpl(
  private val blogRepository: BlogRepository
): BlogDetailDetailService {
  override fun execute(data: BlogDetailRequestDto): BlogDetailResponseDto {
    val blog = findBlog(data.title)

    return BlogDetailResponseDto(blog)
  }

  private fun findBlog(title: String)
    = blogRepository.findById(title).orElseThrow { BasicException(ErrorCode.NOT_FOUND_BLOG) }
}