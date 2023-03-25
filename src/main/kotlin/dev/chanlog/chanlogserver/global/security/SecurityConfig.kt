package dev.chanlog.chanlogserver.global.security

import dev.chanlog.chanlogserver.global.security.auth.provider.AccessAuthenticationProvider
import dev.chanlog.chanlogserver.global.security.auth.provider.RefreshAuthenticationProvider
import dev.chanlog.chanlogserver.global.security.filter.AccessFilter
import dev.chanlog.chanlogserver.global.security.filter.RefreshFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig {
  @Bean
  fun filterChain(
    http: HttpSecurity,
    authenticationManager: AuthenticationManager,
    accessAuthenticationProvider: AccessAuthenticationProvider,
    refreshAuthenticationProvider: RefreshAuthenticationProvider
  ): SecurityFilterChain {
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
      .requestMatchers(HttpMethod.POST, "/blog").hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "/blog").hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE, "/blog").hasRole("ADMIN")

      .requestMatchers(HttpMethod.POST, "/image").hasRole("ADMIN")

      .requestMatchers(HttpMethod.GET, "/test").authenticated()

      .anyRequest().denyAll()

//    http.exceptionHandling()
//      .authenticationEntryPoint()

    http
      .addFilterBefore(AccessFilter(authenticationManager), UsernamePasswordAuthenticationFilter::class.java)
      .addFilterAfter(RefreshFilter(authenticationManager), AccessFilter::class.java)

    return http.build()
  }

  @Bean
  fun PasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}