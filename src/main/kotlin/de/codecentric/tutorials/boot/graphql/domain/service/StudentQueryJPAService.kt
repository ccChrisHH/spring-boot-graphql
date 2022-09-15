package de.codecentric.tutorials.boot.graphql.domain.service

import de.codecentric.tutorials.boot.graphql.domain.StudentQueryService
import de.codecentric.tutorials.boot.graphql.domain.mapper.toDto
import de.codecentric.tutorials.boot.graphql.persistence.StudentRepository
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import org.springframework.stereotype.Service

@Service
class StudentQueryJPAService(
    private val studentRepository: StudentRepository
) : StudentQueryService {
    override fun allStudents(): List<Student> = studentRepository.findAll().map { it.toDto() }

    override fun studentById(id: Int): Student = studentRepository.getReferenceById(id).toDto()

    override fun lazyStudents(): List<Student> = studentRepository.findLazyStudents().map { it.toDto() }

    override fun eagerStudents(): List<Student> = studentRepository.findEagerStudents().map { it.toDto() }
}
