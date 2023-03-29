package dev.chanlog.chanlogserver.domain.blog.controller

import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import dev.chanlog.chanlogserver.domain.blog.service.FindAllBlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController(
  private val findAllBlogService: FindAllBlogService
) {
  @GetMapping
  fun findAllBlog(): List<Blog> = findAllBlogService.execute()
}