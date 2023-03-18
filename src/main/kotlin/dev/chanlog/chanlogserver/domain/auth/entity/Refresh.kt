package dev.chanlog.chanlogserver.domain.auth.entity

import jakarta.persistence.*

@Entity
class Refresh (
  @Column(name = "refresh_token", unique = true)
  val token: String,
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0
}