package dev.chanlogserver.domain.blog.controller

import dev.chanlogserver.domain.blog.dto.request.CreateRequestDto
import dev.chanlogserver.domain.blog.service.CreateBlogService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController(
  private val createBlogService: CreateBlogService
) {
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  fun create(@RequestBody @Valid body: CreateRequestDto, authentication: Authentication)
    = createBlogService.execute(authentication.name, body)
}