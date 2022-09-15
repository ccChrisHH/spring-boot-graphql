package de.codecentric.tutorials.boot.graphql.domain.service

import de.codecentric.tutorials.boot.graphql.domain.CourseQueryService
import de.codecentric.tutorials.boot.graphql.domain.mapper.toDto
import de.codecentric.tutorials.boot.graphql.persistence.CourseRepository
import de.codecentric.tutorials.boot.graphql.web.dto.Course
import org.springframework.stereotype.Service

@Service
class CourseQueryJPAService(
    private val courseRepository: CourseRepository
) : CourseQueryService {
    override fun allCourses(): List<Course> = courseRepository.findAll().map { it.toDto() }

    override fun courseById(id: Int): Course = courseRepository.getReferenceById(id).toDto()

    override fun unpopularCourses(): List<Course> = courseRepository.findUnpopularCourses().map { it.toDto() }

    override fun popularCourses(): List<Course> = courseRepository.findPopularCourses().map { it.toDto() }
}
