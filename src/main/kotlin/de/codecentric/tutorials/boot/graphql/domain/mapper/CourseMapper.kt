package de.codecentric.tutorials.boot.graphql.domain.mapper

import de.codecentric.tutorials.boot.graphql.persistence.entities.CourseEntity
import de.codecentric.tutorials.boot.graphql.web.dto.Course
import de.codecentric.tutorials.boot.graphql.web.dto.SlimCourse

fun CourseEntity.toDto() = Course(
    id = this.id ?: throw IllegalStateException("Retrieved course record without an id. [course=$this]"),
    courseName = this.courseName,
    numberOfStudents = this.students.size,
    students = this.students.map { it.toSlimDto() }
)

fun CourseEntity.toSlimDto() = SlimCourse(
    id = this.id ?: throw IllegalStateException("Retrieved course record without an id. [course=$this]"),
    courseName = this.courseName
)
