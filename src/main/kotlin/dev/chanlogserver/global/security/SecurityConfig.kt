package dev.chanlogserver.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import dev.chanlogserver.global.security.exception.AccessDeniedHandler
import dev.chanlogserver.global.security.exception.CustomAuthenticationEntryPoint
import dev.chanlogserver.global.security.filter.ExceptionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig (
    val objectMapper: ObjectMapper
) {
  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain
    = http
      .csrf().disable()
      .httpBasic().disable()
      .formLogin().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()

      .authorizeHttpRequests()
      .requestMatchers(HttpMethod.GET, "/test").permitAll()
      .requestMatchers(HttpMethod.POST, "/test").permitAll()

      .requestMatchers(HttpMethod.POST, "/auth").permitAll()
      .requestMatchers(HttpMethod.PATCH, "/auth").authenticated()
      .requestMatchers(HttpMethod.DELETE, "/auth").authenticated()

      .requestMatchers(HttpMethod.GET, "/blog").permitAll()
      .requestMatchers(HttpMethod.POST, "/blog").hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "/blog").hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE, "/blog").hasRole("ADMIN")

      .requestMatchers(HttpMethod.POST, "/image").hasRole("ADMIN")

      .anyRequest().permitAll().and()

      .addFilterBefore(ExceptionFilter(objectMapper), UsernamePasswordAuthenticationFilter::class.java)

      .exceptionHandling()
      .authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))
      .accessDeniedHandler(AccessDeniedHandler(objectMapper))
      .and()

      .build()

  @Bean
  fun passwordEncoder(): PasswordEncoder
    = BCryptPasswordEncoder()
}