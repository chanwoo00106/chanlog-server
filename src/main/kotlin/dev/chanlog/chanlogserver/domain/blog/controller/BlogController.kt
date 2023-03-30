package dev.chanlog.chanlogserver.domain.blog.controller

import dev.chanlog.chanlogserver.domain.blog.dto.request.CreateBlogRequestDto
import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import dev.chanlog.chanlogserver.domain.blog.service.CreateBlogService
import dev.chanlog.chanlogserver.domain.blog.service.FindAllBlogService
import jakarta.validation.Valid
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController(
  private val findAllBlogService: FindAllBlogService,
  private val createBlogService: CreateBlogService
) {
  @GetMapping
  fun findAllBlog(@RequestParam("page") page: Int?, @RequestParam("title") title: String?): List<Blog> =
    if (page == null)
      findAllBlogService.execute(0, title)
    else
      findAllBlogService.execute(page, title)

  @PostMapping
  fun createBlog(@RequestBody @Valid data: CreateBlogRequestDto, authentication: Authentication) {
    createBlogService.execute(data, authentication)
  }
}