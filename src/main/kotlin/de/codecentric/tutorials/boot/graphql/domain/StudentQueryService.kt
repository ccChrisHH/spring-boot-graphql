package de.codecentric.tutorials.boot.graphql.domain

import de.codecentric.tutorials.boot.graphql.web.dto.Student

interface StudentQueryService {

    fun allStudents(): List<Student>
    fun studentById(id: Int): Student
    fun lazyStudents(): List<Student>
    fun eagerStudents(): List<Student>
}
