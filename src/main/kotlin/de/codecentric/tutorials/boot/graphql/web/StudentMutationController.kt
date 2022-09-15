package de.codecentric.tutorials.boot.graphql.web

import de.codecentric.tutorials.boot.graphql.domain.StudentMutationService
import de.codecentric.tutorials.boot.graphql.web.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateStudent
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class StudentMutationController(
    private val studentMutationService: StudentMutationService
) {
    @MutationMapping
    fun createStudent(@Argument input: CreateStudent): Student = studentMutationService.createStudent(input)

    @MutationMapping
    fun updateStudent(@Argument input: UpdateStudent): Student = studentMutationService.updateStudent(input)

    @MutationMapping
    fun deleteStudent(@Argument id: Int): Boolean = studentMutationService.deleteStudent(id)
}
