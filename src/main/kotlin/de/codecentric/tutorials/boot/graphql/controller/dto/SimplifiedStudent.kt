package de.codecentric.tutorials.boot.graphql.controller.dto

data class SimplifiedStudent(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val numberOfCourses: Int
)
