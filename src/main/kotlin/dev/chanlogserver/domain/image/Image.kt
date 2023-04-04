package dev.chanlogserver.domain.image

import jakarta.persistence.*

@Entity
data class Image (
  @Column(nullable = false)
  val url: String
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0
}