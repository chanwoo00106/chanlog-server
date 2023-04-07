package dev.chanlogserver.domain.blog.controller

import dev.chanlogserver.domain.blog.dto.request.CreateRequestDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController {
  @PostMapping
  fun create(@RequestBody @Valid body: CreateRequestDto) {

  }
}