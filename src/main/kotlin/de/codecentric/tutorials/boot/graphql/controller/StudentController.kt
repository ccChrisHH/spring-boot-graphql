package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.controller.dto.SimplifiedStudent
import de.codecentric.tutorials.boot.graphql.controller.dto.UpdateStudent
import de.codecentric.tutorials.boot.graphql.db.Course
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import de.codecentric.tutorials.boot.graphql.db.Student
import de.codecentric.tutorials.boot.graphql.db.StudentRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class StudentController(
    private val courseRepository: CourseRepository,
    private val studentRepository: StudentRepository
) {
    @QueryMapping
    fun allStudents(): List<Student> = studentRepository.findAll()

    @QueryMapping
    fun lazyStudents(): List<SimplifiedStudent> = studentRepository.findLazyStudents().map { it.toSimplifiedStudent() }

    @QueryMapping
    fun eagerStudents(): List<SimplifiedStudent> = studentRepository.findEagerStudents().map {
        it.toSimplifiedStudent()
    }

    @MutationMapping
    fun createStudent(@Argument input: CreateStudent) = studentRepository.save(input.toEntity())

    @MutationMapping
    fun updateStudent(@Argument input: UpdateStudent) = input.update()

    @MutationMapping
    fun deleteStudent(@Argument id: Int): Boolean {
        return try {
            val student = studentRepository.getReferenceById(id)
            val courses = courseRepository.findCoursesByStudentsContaining(student)
            courses.forEach {
                val updatedStudents = it.students.minus(student)
                courseRepository.save(
                    Course(
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

    private fun CreateStudent.toEntity() = Student(
        id = null,
        firstName = this.firstName,
        lastName = this.lastName,
        age = this.age,
        courses = emptySet()
    )

    private fun UpdateStudent.update(): Student {
        val existingRecord = studentRepository.getReferenceById(this.id)
        val updatedRecord = Student(
            id = existingRecord.id,
            firstName = this.firstName,
            lastName = this.lastName,
            age = this.age,
            courses = existingRecord.courses
        )
        return studentRepository.save(updatedRecord)
    }

    private fun Student.toSimplifiedStudent() = SimplifiedStudent(
        id = this.id ?: throw IllegalStateException("Retrieved student record without an id. [student=$this]"),
        firstName = this.firstName,
        lastName = this.lastName,
        age = this.age,
        numberOfCourses = this.courses.size
    )
}
