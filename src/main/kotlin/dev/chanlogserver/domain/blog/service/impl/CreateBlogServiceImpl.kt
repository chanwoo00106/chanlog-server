package dev.chanlogserver.domain.blog.service.impl

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.Content
import dev.chanlogserver.domain.blog.dto.dto.ImageDto
import dev.chanlogserver.domain.blog.dto.request.CreateRequestDto
import dev.chanlogserver.domain.blog.dto.response.CreateResponseDto
import dev.chanlogserver.domain.blog.repository.BlogRepository
import dev.chanlogserver.domain.blog.repository.ContentRepository
import dev.chanlogserver.domain.blog.service.CreateBlogService
import dev.chanlogserver.domain.image.Image
import dev.chanlogserver.domain.image.repository.ImageRepository
import dev.chanlogserver.domain.user.User
import dev.chanlogserver.domain.user.repository.UserRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class CreateBlogServiceImpl(
  val userRepository: UserRepository,
  val contentRepository: ContentRepository,
  val blogRepository: BlogRepository, private val imageRepository: ImageRepository
): CreateBlogService {
  override fun execute(email: String, data: CreateRequestDto): CreateResponseDto {
    val user = findUser(email)

    checkDuplicateBlogTitle(data.title, user)

    val blog = createBlog(data, user)

    val images = saveImages(data.images, blog)

    return CreateResponseDto(blog.title, blog.thumbnail, blog.content.content, blog.createdAt, images)
  }

  private fun findUser(email: String)
    = userRepository.findById(email)
    .orElseThrow { BasicException(ErrorCode.INVALID_TOKEN) }

  private fun checkDuplicateBlogTitle(title: String, user: User) {
    val blog = user.blog.find { blog -> blog.title == title }
    if (blog != null) throw BasicException(ErrorCode.DUPLICATE_BLOG_TITLE)
  }

  private fun saveImages(images: List<ImageDto>, blog: Blog)
    = imageRepository.saveAll(images.map { i -> Image(i.image, blog) })

  private fun createBlog(data: CreateRequestDto, user: User): Blog {
    val content = contentRepository.save(Content(data.content))
    val newBlog = Blog(data.title, data.thumbnail, content, user, mutableListOf())

    return blogRepository.save(newBlog)
  }
}