package com.example.kotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "groups")
data class Group(
    @Id var id: String? = null,
    val name: String,
    var usersCount: Int = 0
)