package de.codecentric.tutorials.boot.graphql.web

import de.codecentric.tutorials.boot.graphql.domain.CourseQueryService
import de.codecentric.tutorials.boot.graphql.web.dto.Course
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CourseQueryController(
    val courseQueryService: CourseQueryService
) {
    @QueryMapping
    fun allCourses(): List<Course> = courseQueryService.allCourses()

    @QueryMapping
    fun courseById(@Argument id: Int): Course = courseQueryService.courseById(id)

    @QueryMapping
    fun unpopularCourses(): List<Course> = courseQueryService.unpopularCourses()

    @QueryMapping
    fun popularCourses(): List<Course> = courseQueryService.popularCourses()
}
