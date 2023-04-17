package dev.chanlogserver.domain.image

import dev.chanlogserver.domain.blog.Blog
import jakarta.persistence.*

@Entity
data class Image (
  @Column(nullable = false, unique = true)
  val url: String,

  @ManyToOne
  @JoinColumn(name = "blog_id")
  val blog: Blog
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0
}