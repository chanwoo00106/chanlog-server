package dev.chanlogserver.domain.auth

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Refresh (
  var token: String?
) {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0
}