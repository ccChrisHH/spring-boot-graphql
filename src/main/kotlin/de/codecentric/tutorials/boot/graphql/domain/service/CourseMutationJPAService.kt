package de.codecentric.tutorials.boot.graphql.domain.service

import de.codecentric.tutorials.boot.graphql.domain.CourseMutationService
import de.codecentric.tutorials.boot.graphql.domain.mapper.toDto
import de.codecentric.tutorials.boot.graphql.persistence.CourseRepository
import de.codecentric.tutorials.boot.graphql.persistence.entities.CourseEntity
import de.codecentric.tutorials.boot.graphql.web.dto.Course
import de.codecentric.tutorials.boot.graphql.web.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateCourse
import org.springframework.stereotype.Service

@Service
class CourseMutationJPAService(
    private val courseRepository: CourseRepository
) : CourseMutationService {
    override fun createCourse(input: CreateCourse): Course = courseRepository.save(
        CourseEntity(
            id = null,
            courseName = input.courseName,
            students = emptyList()
        )
    ).toDto()

    override fun updateCourse(input: UpdateCourse): Course {
        val existingRecord = courseRepository.getReferenceById(input.id)
        val updatedRecord = CourseEntity(
            id = existingRecord.id,
            courseName = input.courseName,
            students = existingRecord.students
        )
        return courseRepository.save(updatedRecord).toDto()
    }

    override fun deleteCourse(id: Int): Boolean {
        return try {
            courseRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}
