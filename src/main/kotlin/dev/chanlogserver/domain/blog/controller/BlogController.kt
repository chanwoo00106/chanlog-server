package dev.chanlogserver.domain.blog.controller

import dev.chanlogserver.domain.blog.Blog
import dev.chanlogserver.domain.blog.dto.request.CreateRequestDto
import dev.chanlogserver.domain.blog.dto.request.FindBlogRequestDto
import dev.chanlogserver.domain.blog.dto.request.ModifyBlogRequestDto
import dev.chanlogserver.domain.blog.dto.response.FindBlogResponseDto
import dev.chanlogserver.domain.blog.service.CreateBlogService
import dev.chanlogserver.domain.blog.service.FindBlogService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
@Validated
class BlogController(
  private val createBlogService: CreateBlogService,
  private val findBlogService: FindBlogService
) {
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  fun create(@RequestBody @Valid body: CreateRequestDto, authentication: Authentication)
    = createBlogService.execute(authentication.name, body)

  @GetMapping
  fun findBlog(
    @RequestParam("title") title: String?,
    @RequestParam("page") page: Int?,
    @RequestParam("email") email: String?
  ): List<FindBlogResponseDto> {
    return findBlogService.execute(FindBlogRequestDto(title, page, email))
  }

  @PutMapping
  fun modifyBlog(
    @RequestParam("title") @NotEmpty(message = "title은 필수값입니다") title: String,
    @RequestBody @Valid body: ModifyBlogRequestDto
  ) {}
}