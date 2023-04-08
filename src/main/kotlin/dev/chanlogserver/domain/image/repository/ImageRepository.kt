package dev.chanlogserver.domain.image.repository

import dev.chanlogserver.domain.image.Image
import org.springframework.data.repository.CrudRepository

interface ImageRepository: CrudRepository<Image, Long> {
}