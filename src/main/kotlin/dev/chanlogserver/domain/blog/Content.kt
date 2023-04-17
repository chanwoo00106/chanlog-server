package dev.chanlogserver.domain.blog

import jakarta.persistence.*

@Entity
data class Content (
  @Column(nullable = false)
  var content: String
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0
}
