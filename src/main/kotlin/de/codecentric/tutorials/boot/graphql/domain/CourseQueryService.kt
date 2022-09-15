package de.codecentric.tutorials.boot.graphql.domain

import de.codecentric.tutorials.boot.graphql.web.dto.Course

interface CourseQueryService {

    fun allCourses(): List<Course>
    fun courseById(id: Int): Course
    fun unpopularCourses(): List<Course>
    fun popularCourses(): List<Course>
}
