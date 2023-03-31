package dev.chanlog.chanlogserver.domain.blog.service.impl

import dev.chanlog.chanlogserver.domain.blog.dto.request.CreateBlogRequestDto
import dev.chanlog.chanlogserver.domain.blog.dto.response.CreateBlogResponseDto
import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import dev.chanlog.chanlogserver.domain.blog.entity.Content
import dev.chanlog.chanlogserver.domain.blog.repository.BlogRepository
import dev.chanlog.chanlogserver.domain.blog.repository.ContentRepository
import dev.chanlog.chanlogserver.domain.blog.service.CreateBlogService
import dev.chanlog.chanlogserver.domain.user.entity.User
import dev.chanlog.chanlogserver.domain.user.repository.UserRepository
import dev.chanlog.chanlogserver.global.exception.BasicException
import dev.chanlog.chanlogserver.global.exception.ErrorCode
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class CreateBlogServiceImpl(
  val userRepository: UserRepository,
  val contentRepository: ContentRepository,
  val blogRepository: BlogRepository
): CreateBlogService {
  override fun execute(data: CreateBlogRequestDto, authentication: Authentication): CreateBlogResponseDto {
    checkBlog(data.title)
    val user = findUser(authentication.name)
    val content = createContent(data.content)
    val blog = createBlog(data, user, content)

    return CreateBlogResponseDto(blog)
  }

  private fun findUser(id: String)
    = userRepository.findById(id).orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

  private fun checkBlog(title: String) {
    val blog = blogRepository.findById(title).isEmpty
    if (!blog) throw BasicException(ErrorCode.ALREADY_EXIST_BLOG)
  }

  private fun createContent(content: String)
    = contentRepository.save(Content(content))

  private fun createBlog(data: CreateBlogRequestDto, user: User, content: Content)
    = blogRepository.save(Blog(data.title, data.thumbnail, user, content))
}