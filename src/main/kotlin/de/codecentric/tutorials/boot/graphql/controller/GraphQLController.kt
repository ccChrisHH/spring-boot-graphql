package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.db.Course
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import de.codecentric.tutorials.boot.graphql.db.Student
import de.codecentric.tutorials.boot.graphql.db.StudentRepository
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
}
