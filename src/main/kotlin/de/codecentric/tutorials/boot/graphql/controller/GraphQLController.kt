package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.controller.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.controller.dto.CreateStudent
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
    fun createStudent(@Argument input: CreateStudent) = studentRepository.saveAndFlush(input.toEntity())

    @MutationMapping
    fun createCourse(@Argument input: CreateCourse) = courseRepository.saveAndFlush(input.toEntity())

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
}
