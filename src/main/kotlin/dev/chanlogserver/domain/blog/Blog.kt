package dev.chanlogserver.domain.blog

import dev.chanlogserver.domain.image.Image
import dev.chanlogserver.domain.user.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

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
  val createdAt: LocalDateTime = LocalDateTime.now()

  @Column(name = "updated_at", nullable = true)
  var updatedAt: LocalDateTime? = null
}