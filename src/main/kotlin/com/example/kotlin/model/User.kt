package com.example.kotlin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id var id: String? = null,
    val name: Name,
    val address: Address,
    val avatar: String
)

class Name(
    val firstName: String,
    val lastName: String
)

data class Address(
    val address: String,
    val city: String,
    val state: String,
    val country: String,
    val zipCode: String
)