package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMongoDbApplication

fun main(args: Array<String>) {
    runApplication<KotlinMongoDbApplication>(*args)
}
