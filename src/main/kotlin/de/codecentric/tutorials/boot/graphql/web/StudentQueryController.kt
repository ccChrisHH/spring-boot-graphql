package de.codecentric.tutorials.boot.graphql.web

import de.codecentric.tutorials.boot.graphql.domain.StudentQueryService
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class StudentQueryController(
    private val studentQueryService: StudentQueryService
) {
    @QueryMapping
    fun allStudents(): List<Student> = studentQueryService.allStudents()

    @QueryMapping
    fun studentById(@Argument id: Int): Student = studentQueryService.studentById(id)

    @QueryMapping
    fun lazyStudents(): List<Student> = studentQueryService.lazyStudents()

    @QueryMapping
    fun eagerStudents(): List<Student> = studentQueryService.eagerStudents()
}
