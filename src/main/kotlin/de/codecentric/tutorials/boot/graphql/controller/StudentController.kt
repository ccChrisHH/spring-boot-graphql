package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.controller.dto.Student
import de.codecentric.tutorials.boot.graphql.controller.dto.UpdateStudent
import de.codecentric.tutorials.boot.graphql.controller.mapper.toDto
import de.codecentric.tutorials.boot.graphql.controller.mapper.toEntity
import de.codecentric.tutorials.boot.graphql.db.CourseEntity
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import de.codecentric.tutorials.boot.graphql.db.StudentEntity
import de.codecentric.tutorials.boot.graphql.db.StudentRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class StudentController(
    private val courseRepository: CourseRepository,
    private val studentRepository: StudentRepository
) {
    @MutationMapping
    fun createStudent(@Argument input: CreateStudent): Student = studentRepository.save(input.toEntity()).toDto()

    @MutationMapping
    fun updateStudent(@Argument input: UpdateStudent): Student = input.update().toDto()

    @MutationMapping
    fun deleteStudent(@Argument id: Int): Boolean {
        return try {
            val student = studentRepository.getReferenceById(id)
            val courses = courseRepository.findCoursesByStudentsContaining(student)
            courses.forEach {
                val updatedStudents = it.students.minus(student)
                courseRepository.save(
                    CourseEntity(
                        id = it.id,
                        courseName = it.courseName,
                        students = updatedStudents
                    )
                )
            }
            studentRepository.delete(student)
            true
        } catch (e: Exception) {
            return false
        }
    }

    private fun UpdateStudent.update(): StudentEntity {
        val existingRecord = studentRepository.getReferenceById(this.id)
        val updatedRecord = StudentEntity(
            id = existingRecord.id,
            firstName = this.firstName,
            lastName = this.lastName,
            age = this.age,
            courses = existingRecord.courses
        )
        return studentRepository.save(updatedRecord)
    }
}
