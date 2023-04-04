package dev.chanlogserver.domain.blog

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Content (
  @Column(nullable = false)
  val content: String
) {
  @Id
  val id: Long = 0
}
