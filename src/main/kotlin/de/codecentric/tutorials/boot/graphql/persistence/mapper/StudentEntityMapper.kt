package de.codecentric.tutorials.boot.graphql.persistence.mapper

import de.codecentric.tutorials.boot.graphql.web.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.persistence.entities.StudentEntity

fun CreateStudent.toEntity() = StudentEntity(
    id = null,
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age,
    courses = emptyList()
)
