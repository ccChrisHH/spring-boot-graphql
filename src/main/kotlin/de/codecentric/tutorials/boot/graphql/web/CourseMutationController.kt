package de.codecentric.tutorials.boot.graphql.web

import de.codecentric.tutorials.boot.graphql.domain.CourseMutationService
import de.codecentric.tutorials.boot.graphql.web.dto.Course
import de.codecentric.tutorials.boot.graphql.web.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateCourse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class CourseMutationController(
    val courseMutationService: CourseMutationService
) {
    @MutationMapping
    fun createCourse(@Argument input: CreateCourse): Course = courseMutationService.createCourse(input)

    @MutationMapping
    fun updateCourse(@Argument input: UpdateCourse): Course = courseMutationService.updateCourse(input)

    @MutationMapping
    fun deleteCourse(@Argument id: Int): Boolean = courseMutationService.deleteCourse(id)
}
