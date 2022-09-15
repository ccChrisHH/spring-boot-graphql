package de.codecentric.tutorials.boot.graphql.domain

import de.codecentric.tutorials.boot.graphql.web.dto.CreateStudent
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateStudent

interface StudentMutationService {

    fun createStudent(input: CreateStudent): Student
    fun updateStudent(input: UpdateStudent): Student
    fun deleteStudent(id: Int): Boolean
}
