package dev.chanlog.chanlogserver.domain.blog.service.impl

import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import dev.chanlog.chanlogserver.domain.blog.repository.BlogRepository
import dev.chanlog.chanlogserver.domain.blog.service.FindAllBlogService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FindAllBlogServiceImpl(
  private val blogRepository: BlogRepository
): FindAllBlogService {
  override fun execute(page: Int): List<Blog>
    = blogRepository.findAll(PageRequest.of(page * 30, (page + 1) * 30)).toList()
}