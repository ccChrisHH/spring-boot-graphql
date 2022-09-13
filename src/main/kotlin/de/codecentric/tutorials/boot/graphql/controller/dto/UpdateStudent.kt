package de.codecentric.tutorials.boot.graphql.controller.dto

data class UpdateStudent(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)
