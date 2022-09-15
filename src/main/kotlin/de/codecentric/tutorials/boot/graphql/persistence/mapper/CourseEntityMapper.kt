package de.codecentric.tutorials.boot.graphql.persistence.mapper

import de.codecentric.tutorials.boot.graphql.web.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.persistence.entities.CourseEntity

fun CreateCourse.toEntity() = CourseEntity(
    id = null,
    courseName = this.courseName,
    students = emptyList()
)
