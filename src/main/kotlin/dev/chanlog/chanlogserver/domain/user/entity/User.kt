package dev.chanlog.chanlogserver.domain.user.entity

import dev.chanlog.chanlogserver.domain.auth.entity.Refresh
import dev.chanlog.chanlogserver.domain.blog.entity.Blog
import dev.chanlog.chanlogserver.domain.user.Role
import jakarta.persistence.*

@Entity
class User (
  @Id
  val id: String,

  @Column(nullable = false)
  val password: String,

  @Column(nullable = false)
  val name: String,

  @Column(nullable = true)
  val img: String?,

  @Enumerated(EnumType.STRING)
  val role: Role,

  @OneToMany(mappedBy = "user")
  val blogs: MutableList<Blog>,

  @OneToOne
  @JoinColumn(name = "refresh_id")
  val refreshToken: Refresh
)