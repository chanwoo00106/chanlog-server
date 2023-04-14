package dev.chanlogserver.domain.blog.service.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.chanlogserver.domain.blog.QBlog
import dev.chanlogserver.domain.blog.dto.request.FindBlogRequestDto
import dev.chanlogserver.domain.blog.dto.response.FindBlogResponseDto
import dev.chanlogserver.domain.blog.service.FindBlogService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FindBlogServiceImpl(
  private val jpaQueryFactory: JPAQueryFactory
): FindBlogService {
  override fun execute(data: FindBlogRequestDto): List<FindBlogResponseDto> {
    val blog = QBlog.blog
    val pageRequest = if (data.page != null) PageRequest.of(data.page, 10) else PageRequest.of(0, 10)

    val blogs = jpaQueryFactory.selectFrom(blog)
      .where(
        blog.title.contains(data.title ?: ""),
        if (data.email != null) blog.user.email.eq(data.email) else null
      )
      .offset(pageRequest.offset)
      .limit(pageRequest.pageSize.toLong())
      .orderBy(blog.updatedAt.desc())
      .orderBy(blog.createdAt.desc())
      .fetch()

    return blogs.map { blog -> FindBlogResponseDto(blog) }
  }
}