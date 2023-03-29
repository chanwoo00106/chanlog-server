package dev.chanlog.chanlogserver.domain.blog.service

import dev.chanlog.chanlogserver.domain.blog.entity.Blog

interface FindAllBlogService {
  fun execute(): List<Blog>
}