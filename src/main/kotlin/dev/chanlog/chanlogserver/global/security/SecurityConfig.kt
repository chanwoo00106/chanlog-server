package dev.chanlog.chanlogserver.global.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig {
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http.cors().and()
      .csrf().disable()
      .formLogin().disable()
      .httpBasic().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    http.authorizeHttpRequests()
      .requestMatchers(RequestMatcher { request ->
      CorsUtils.isPreFlightRequest(request)
      }).permitAll()
      .requestMatchers(HttpMethod.POST, "/auth").permitAll()
      .requestMatchers(HttpMethod.PATCH, "/auth").authenticated()
      .requestMatchers(HttpMethod.DELETE, "/auth").authenticated()

      .requestMatchers(HttpMethod.GET, "/blog").permitAll()
      .requestMatchers(HttpMethod.POST, "/blog").authenticated()
      .requestMatchers(HttpMethod.PUT, "/blog").authenticated()
      .requestMatchers(HttpMethod.DELETE, "/blog").authenticated()

      .requestMatchers(HttpMethod.POST, "/image").authenticated()

      .anyRequest().denyAll()

//    http.exceptionHandling()
//      .authenticationEntryPoint()

    return http.build()
  }
}