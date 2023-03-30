package dev.chanlog.chanlogserver.domain.blog.controller

import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import dev.chanlog.chanlogserver.domain.blog.service.FindAllBlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController(
  private val findAllBlogService: FindAllBlogService
) {
  @GetMapping
  fun findAllBlog(@RequestParam("page") page: Int?, @RequestParam("title") title: String?): List<Blog> {
    if (page == null)
      return findAllBlogService.execute(0, title)

    return findAllBlogService.execute(page, title)
  }
}