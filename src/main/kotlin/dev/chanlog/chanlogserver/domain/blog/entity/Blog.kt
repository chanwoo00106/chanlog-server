package dev.chanlog.chanlogserver.domain.blog.entity

import dev.chanlog.chanlogserver.domain.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import java.time.LocalDateTime

@Entity
class Blog (
  @Id
  val title: String,

  @Column(nullable = false)
  val thumbnail: String,

  @ManyToOne()
  @JoinColumn(name = "user_id")
  var user: User,

  @OneToOne
  @JoinColumn(name = "content_id")
  val content: Content
) {
  @Column(name = "created_at", nullable = false)
  val createdAt: LocalDateTime = LocalDateTime.now()

  @Column(name = "updated_at", nullable = true)
  val updatedAt: LocalDateTime? = null
}