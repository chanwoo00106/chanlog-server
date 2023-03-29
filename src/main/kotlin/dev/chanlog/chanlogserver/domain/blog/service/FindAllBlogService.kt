package dev.chanlog.chanlogserver.domain.blog.service

import dev.chanlog.chanlogserver.domain.blog.entity.Blog

interface FindAllBlogService {
  fun execute(page: Int): List<Blog>
}