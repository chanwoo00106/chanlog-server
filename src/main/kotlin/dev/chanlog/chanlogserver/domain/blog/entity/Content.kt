package dev.chanlog.chanlogserver.domain.blog.entity

import jakarta.persistence.*

@Entity
class Content (
  @Column(nullable = false)
  val content: String,
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0
}