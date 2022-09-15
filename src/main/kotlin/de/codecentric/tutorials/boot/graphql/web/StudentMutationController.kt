package de.codecentric.tutorials.boot.graphql.web

import de.codecentric.tutorials.boot.graphql.web.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateStudent
import de.codecentric.tutorials.boot.graphql.web.mapper.toDto
import de.codecentric.tutorials.boot.graphql.persistence.CourseRepository
import de.codecentric.tutorials.boot.graphql.persistence.StudentRepository
import de.codecentric.tutorials.boot.graphql.persistence.entities.CourseEntity
import de.codecentric.tutorials.boot.graphql.persistence.entities.StudentEntity
import de.codecentric.tutorials.boot.graphql.persistence.mapper.toEntity
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class StudentMutationController(
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
