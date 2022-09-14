package de.codecentric.tutorials.boot.graphql.adapter.outbound.mapper

import de.codecentric.tutorials.boot.graphql.adapter.inbound.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.adapter.outbound.entities.StudentEntity

fun CreateStudent.toEntity() = StudentEntity(
    id = null,
    firstName = this.firstName,
    lastName = this.lastName,
    age = this.age,
    courses = emptyList()
)
