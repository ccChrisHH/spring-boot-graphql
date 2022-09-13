package de.codecentric.tutorials.boot.graphql.controller.dto

data class CreateStudent(
    val firstName: String,
    val lastName: String,
    val age: Int
)
