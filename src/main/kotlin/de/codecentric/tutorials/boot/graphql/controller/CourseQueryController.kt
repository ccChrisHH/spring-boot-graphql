package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.Course
import de.codecentric.tutorials.boot.graphql.controller.mapper.toDto
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CourseQueryController(
    val courseRepository: CourseRepository
) {
    @QueryMapping
    fun allCourses(): List<Course> = courseRepository.findAll().map { it.toDto() }

    @QueryMapping
    fun courseById(@Argument id: Int): Course = courseRepository.getReferenceById(id).toDto()

    @QueryMapping
    fun unpopularCourses(): List<Course> = courseRepository.findUnpopularCourses().map { it.toDto() }

    @QueryMapping
    fun popularCourses(): List<Course> = courseRepository.findPopularCourses().map { it.toDto() }
}
