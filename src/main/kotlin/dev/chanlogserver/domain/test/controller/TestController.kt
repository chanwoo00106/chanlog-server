package dev.chanlogserver.domain.test.controller

import dev.chanlogserver.domain.test.dto.request.SayHelloRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
  @GetMapping
  fun sayHello(@RequestParam num: Int): String {
    return "Hello $num"
  }

  @PostMapping
  fun sayHello(@Valid @RequestBody body: SayHelloRequest): String {
    return "Hello, ${body.test}"
  }
}