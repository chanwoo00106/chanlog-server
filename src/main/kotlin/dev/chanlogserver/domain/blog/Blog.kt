package dev.chanlogserver.domain.blog

import dev.chanlogserver.domain.image.Image
import dev.chanlogserver.domain.user.User
import jakarta.persistence.*
import java.time.LocalTime

@Entity
class Blog (
  @Id
  val title: String,

  @Column(nullable = false)
  val thumbnail: String,

  @OneToOne
  val content: Content,

  @ManyToOne
  @JoinColumn(name = "user_id")
  val user: User,

  @OneToMany(mappedBy = "blog")
  val images: MutableList<Image>
) {
  @Column(name = "created_at", nullable = false)
  val createdAt: LocalTime = LocalTime.now()

  @Column(name = "updated_at", nullable = true)
  var updatedAt: LocalTime? = null
}