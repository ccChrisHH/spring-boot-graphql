package de.codecentric.tutorials.boot.graphql.adapter.inbound

import de.codecentric.tutorials.boot.graphql.adapter.inbound.dto.Student
import de.codecentric.tutorials.boot.graphql.adapter.inbound.mapper.toDto
import de.codecentric.tutorials.boot.graphql.adapter.outbound.StudentRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class StudentQueryController(
    private val studentRepository: StudentRepository
) {
    @QueryMapping
    fun allStudents(): List<Student> = studentRepository.findAll().map { it.toDto() }

    @QueryMapping
    fun studentById(@Argument id: Int): Student = studentRepository.getReferenceById(id).toDto()

    @QueryMapping
    fun lazyStudents(): List<Student> = studentRepository.findLazyStudents().map { it.toDto() }

    @QueryMapping
    fun eagerStudents(): List<Student> = studentRepository.findEagerStudents().map { it.toDto() }
}
