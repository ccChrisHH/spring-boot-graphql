package de.codecentric.tutorials.boot.graphql.domain.service

import de.codecentric.tutorials.boot.graphql.domain.StudentMutationService
import de.codecentric.tutorials.boot.graphql.domain.mapper.toDto
import de.codecentric.tutorials.boot.graphql.persistence.CourseRepository
import de.codecentric.tutorials.boot.graphql.persistence.StudentRepository
import de.codecentric.tutorials.boot.graphql.persistence.entities.CourseEntity
import de.codecentric.tutorials.boot.graphql.persistence.entities.StudentEntity
import de.codecentric.tutorials.boot.graphql.web.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateStudent
import org.springframework.stereotype.Service

@Service
class StudentMutationJPAService(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository
) : StudentMutationService {
    override fun createStudent(input: CreateStudent): Student = studentRepository.save(
        StudentEntity(
            id = null,
            firstName = input.firstName,
            lastName = input.lastName,
            age = input.age,
            courses = emptyList()
        )
    ).toDto()

    override fun updateStudent(input: UpdateStudent): Student {
        val existingRecord = studentRepository.getReferenceById(input.id)
        val updatedRecord = StudentEntity(
            id = existingRecord.id,
            firstName = input.firstName,
            lastName = input.lastName,
            age = input.age,
            courses = existingRecord.courses
        )
        return studentRepository.save(updatedRecord).toDto()
    }

    override fun deleteStudent(id: Int): Boolean {
        return try {
            val student = studentRepository.getReferenceById(id)
            val courses = courseRepository.findCoursesByStudentsContaining(student)
            val updatedCourses = courses.map {
                CourseEntity(
                    id = it.id,
                    courseName = it.courseName,
                    students = it.students.minus(student)
                )
            }
            courseRepository.saveAll(updatedCourses)
            studentRepository.delete(student)
            true
        } catch (e: Exception) {
            return false
        }
    }
}
