package de.codecentric.tutorials.boot.graphql.controller.mapper

import de.codecentric.tutorials.boot.graphql.controller.dto.Course
import de.codecentric.tutorials.boot.graphql.controller.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.controller.dto.SlimCourse
import de.codecentric.tutorials.boot.graphql.db.CourseEntity

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

fun CreateCourse.toEntity() = CourseEntity(
    id = null,
    courseName = this.courseName,
    students = emptyList()
)


