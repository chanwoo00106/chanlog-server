package dev.chanlog.chanlogserver.domain.auth.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Refresh (
  @Column(name = "refresh_token", unique = true)
  var token: String,
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0

  @Column(name = "created_at")
  val createdAt: LocalDateTime = LocalDateTime.now()
}