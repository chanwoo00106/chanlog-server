package dev.chanlogserver.domain.blog.service.impl

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.dto.ImageDto
import dev.chanlogserver.domain.blog.dto.request.ModifyBlogRequestDto
import dev.chanlogserver.domain.blog.dto.response.ModifyBlogResponseDto
import dev.chanlogserver.domain.blog.repository.BlogRepository
import dev.chanlogserver.domain.blog.service.ModifyBlogService
import dev.chanlogserver.domain.image.Image
import dev.chanlogserver.domain.image.repository.ImageRepository
import dev.chanlogserver.global.exception.BasicException
import dev.chanlogserver.global.exception.ErrorCode
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ModifyBlogServiceImpl(
  private val blogRepository: BlogRepository,
  private val imageRepository: ImageRepository
): ModifyBlogService {
  override fun execute(title: String, body: ModifyBlogRequestDto): ModifyBlogResponseDto {
    val blog = findBlog(title)

    val newBlog = createNewBlog(title, body, blog)

    return ModifyBlogResponseDto(newBlog)
  }

  private fun findBlog(title: String)
    = blogRepository.findById(title)
      .orElseThrow { BasicException(ErrorCode.NOT_FOUND_BLOG) }

  private fun createNewBlog(title: String, body: ModifyBlogRequestDto, currentBlog: Blog): Blog {
    currentBlog.content.content = body.content

    saveImages(body.images, currentBlog)
    val newBlog = Blog(title, body.thumbnail, currentBlog.content, currentBlog.user, currentBlog.images, currentBlog.createdAt)
    newBlog.updatedAt = LocalDateTime.now()
    return blogRepository.save(newBlog)
  }

  private fun saveImages(images: List<ImageDto>, blog: Blog) {
    val newImages = images.map { image -> Image(image.image, blog) }
    imageRepository.saveAll(newImages)
    blog.images.addAll(newImages)
  }
}