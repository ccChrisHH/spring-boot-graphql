package de.codecentric.tutorials.boot.graphql.controller.dto

data class Course(
    val id: Int,
    val courseName: String,
    val numberOfStudents: Int,
    val students: List<SlimStudent>
)

data class SlimCourse(
    val id: Int,
    val courseName: String
)

data class CreateCourse(
    val courseName: String
)

data class UpdateCourse(
    val id: Int,
    val courseName: String
)
