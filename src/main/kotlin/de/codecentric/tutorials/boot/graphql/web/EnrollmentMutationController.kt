package de.codecentric.tutorials.boot.graphql.web

import de.codecentric.tutorials.boot.graphql.domain.EnrollmentMutationService
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class EnrollmentMutationController(
    private val enrollmentMutationService: EnrollmentMutationService
) {
    @MutationMapping
    fun enrollStudentInCourse(@Argument studentId: Int, @Argument courseId: Int): Student =
        enrollmentMutationService.enrollStudentInCourse(studentId = studentId, courseId = courseId)

    @MutationMapping
    fun unEnrollStudentFromCourse(@Argument studentId: Int, @Argument courseId: Int): Student =
        enrollmentMutationService.unEnrollStudentFromCourse(studentId = studentId, courseId = courseId)
}
