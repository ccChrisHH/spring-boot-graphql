package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.Course
import de.codecentric.tutorials.boot.graphql.controller.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.controller.dto.UpdateCourse
import de.codecentric.tutorials.boot.graphql.controller.mapper.toDto
import de.codecentric.tutorials.boot.graphql.controller.mapper.toEntity
import de.codecentric.tutorials.boot.graphql.db.CourseEntity
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class CourseMutationController(
    val courseRepository: CourseRepository
) {
    @MutationMapping
    fun createCourse(@Argument input: CreateCourse): Course = courseRepository.save(input.toEntity()).toDto()

    @MutationMapping
    fun updateCourse(@Argument input: UpdateCourse): Course = input.update().toDto()

    @MutationMapping
    fun deleteCourse(@Argument id: Int): Boolean {
        return try {
            courseRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun UpdateCourse.update(): CourseEntity {
        val existingRecord = courseRepository.getReferenceById(this.id)
        val updatedRecord = CourseEntity(
            id = existingRecord.id,
            courseName = this.courseName,
            students = existingRecord.students
        )
        return courseRepository.save(updatedRecord)
    }
}
