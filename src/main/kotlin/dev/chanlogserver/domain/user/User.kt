package dev.chanlogserver.domain.user

import dev.chanlogserver.domain.auth.Refresh
import dev.chanlogserver.domain.blog.Blog
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
data class User (
  @Id
  val email: String,

  @Column(nullable = false)
  val password: String,

  @Column(nullable = false, length = 20)
  val name: String,

  @Column(nullable = true)
  var img: String?,

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  val role: Role,

  @OneToOne(mappedBy = "user")
  val refresh: Refresh
) {
  @OneToMany(mappedBy = "user")
  val blog: MutableList<Blog> = mutableListOf<Blog>()
}