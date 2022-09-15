package de.codecentric.tutorials.boot.graphql.domain

import de.codecentric.tutorials.boot.graphql.web.dto.Student

interface EnrollmentMutationService {

    fun enrollStudentInCourse(studentId: Int, courseId: Int): Student
    fun unEnrollStudentFromCourse(studentId: Int, courseId: Int): Student
}
