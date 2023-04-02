package dev.chanlogserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChanlogServerApplication

fun main(args: Array<String>) {
	runApplication<ChanlogServerApplication>(*args)
}
