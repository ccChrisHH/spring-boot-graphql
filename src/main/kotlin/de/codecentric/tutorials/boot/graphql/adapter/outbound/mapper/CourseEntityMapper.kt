package de.codecentric.tutorials.boot.graphql.adapter.outbound.mapper

import de.codecentric.tutorials.boot.graphql.adapter.inbound.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.adapter.outbound.entities.CourseEntity

fun CreateCourse.toEntity() = CourseEntity(
    id = null,
    courseName = this.courseName,
    students = emptyList()
)
