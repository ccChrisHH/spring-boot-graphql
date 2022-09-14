package de.codecentric.tutorials.boot.graphql.adapter.inbound.dto

data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val numberOfCourses: Int,
    val courses: List<SlimCourse>
)

data class SlimStudent(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)

data class CreateStudent(
    val firstName: String,
    val lastName: String,
    val age: Int
)

data class UpdateStudent(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
)
