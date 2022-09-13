package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.controller.dto.SimplifiedCourse
import de.codecentric.tutorials.boot.graphql.controller.dto.UpdateCourse
import de.codecentric.tutorials.boot.graphql.db.Course
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CourseController(
    val courseRepository: CourseRepository
) {
    @QueryMapping
    fun allCourses(): List<Course> = courseRepository.findAll()

    @QueryMapping
    fun unpopularCourses(): List<SimplifiedCourse> =
        courseRepository.findUnpopularCourses().map { it.toSimplifiedCourse() }

    @QueryMapping
    fun popularCourses(): List<SimplifiedCourse> =
        courseRepository.findPopularCourses().map { it.toSimplifiedCourse() }

    @MutationMapping
    fun createCourse(@Argument input: CreateCourse) = courseRepository.save(input.toEntity())

    @MutationMapping
    fun updateCourse(@Argument input: UpdateCourse) = input.update()

    @MutationMapping
    fun deleteCourse(@Argument id: Int): Boolean {
        return try {
            courseRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun CreateCourse.toEntity() = Course(
        id = null,
        courseName = this.courseName,
        students = emptySet()
    )

    private fun UpdateCourse.update(): Course {
        val existingRecord = courseRepository.getReferenceById(this.id)
        val updatedRecord = Course(
            id = existingRecord.id,
            courseName = this.courseName,
            students = existingRecord.students
        )
        return courseRepository.save(updatedRecord)
    }

    private fun Course.toSimplifiedCourse() = SimplifiedCourse(
        id = this.id ?: throw IllegalStateException("Retrieved course record without an id. [course=$this]"),
        courseName = this.courseName,
        numberOfStudents = this.students.size
    )
}
