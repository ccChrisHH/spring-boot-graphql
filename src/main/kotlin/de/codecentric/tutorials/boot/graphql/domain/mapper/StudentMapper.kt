package de.codecentric.tutorials.boot.graphql.domain.mapper

import de.codecentric.tutorials.boot.graphql.persistence.entities.StudentEntity
import de.codecentric.tutorials.boot.graphql.web.dto.SlimStudent
import de.codecentric.tutorials.boot.graphql.web.dto.Student

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
