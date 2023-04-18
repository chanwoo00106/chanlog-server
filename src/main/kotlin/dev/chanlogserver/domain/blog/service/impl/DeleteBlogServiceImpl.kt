package dev.chanlogserver.domain.blog.service.impl

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.request.DeleteBlogRequestDto
import dev.chanlogserver.domain.blog.repository.BlogRepository
import dev.chanlogserver.domain.blog.service.DeleteBlogService
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class DeleteBlogServiceImpl(
  private val blogRepository: BlogRepository
): DeleteBlogService {
  override fun execute(data: DeleteBlogRequestDto) {
    val blog = findBlog(data.title)

    deleteBlog(blog)
  }

  private fun findBlog(title: String)
    = blogRepository.findById(title)
      .orElseThrow { BasicException(ErrorCode.NOT_FOUND_BLOG) }

  private fun deleteBlog(blog: Blog) {
    blogRepository.delete(blog)
  }
}