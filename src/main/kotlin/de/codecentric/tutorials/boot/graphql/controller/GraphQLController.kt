package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.controller.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.controller.dto.UpdateCourse
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
class GraphQLController(
    private val courseRepository: CourseRepository,
    private val studentRepository: StudentRepository
) {

    @QueryMapping
    fun allCourses(): List<Course> = courseRepository.findAll()

    @QueryMapping
    fun allStudents(): List<Student> = studentRepository.findAll()

    @MutationMapping
    fun createStudent(@Argument input: CreateStudent) = studentRepository.save(input.toEntity())

    @MutationMapping
    fun createCourse(@Argument input: CreateCourse) = courseRepository.save(input.toEntity())

    @MutationMapping
    fun updateStudent(@Argument input: UpdateStudent) = input.update()

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

    private fun CreateCourse.toEntity() = Course(
        id = null,
        courseName = this.courseName,
        students = emptySet()
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

    private fun UpdateCourse.update(): Course {
        val existingRecord = courseRepository.getReferenceById(this.id)
        val updatedRecord = Course(
            id = existingRecord.id,
            courseName = this.courseName,
            students = existingRecord.students
        )
        return courseRepository.save(updatedRecord)
    }
}
