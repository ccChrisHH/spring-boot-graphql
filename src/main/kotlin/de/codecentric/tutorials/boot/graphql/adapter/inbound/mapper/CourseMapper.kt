package de.codecentric.tutorials.boot.graphql.adapter.inbound.mapper

import de.codecentric.tutorials.boot.graphql.adapter.inbound.dto.Course
import de.codecentric.tutorials.boot.graphql.adapter.inbound.dto.SlimCourse
import de.codecentric.tutorials.boot.graphql.adapter.outbound.entities.CourseEntity

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
