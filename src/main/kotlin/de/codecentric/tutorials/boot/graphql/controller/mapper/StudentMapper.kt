package de.codecentric.tutorials.boot.graphql.controller.mapper

import de.codecentric.tutorials.boot.graphql.controller.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.controller.dto.SlimStudent
import de.codecentric.tutorials.boot.graphql.controller.dto.Student
import de.codecentric.tutorials.boot.graphql.db.StudentEntity

fun StudentEntity.toDto() = Student(
    id = this.id ?: throw IllegalStateException("Retrieved student record without an id. [course=$this]"),
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age,
    numberOfCourses = this.courses.size,
    courses = this.courses.map { it.toSlimDto() }
)

fun StudentEntity.toSlimDto() = SlimStudent(
    id = this.id ?: throw IllegalStateException("Retrieved student record without an id. [course=$this]"),
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age
)

fun CreateStudent.toEntity() = StudentEntity(
    id = null,
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age,
    courses = emptyList()
)
