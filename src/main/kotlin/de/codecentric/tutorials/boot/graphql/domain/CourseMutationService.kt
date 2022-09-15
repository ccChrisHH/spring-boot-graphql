package de.codecentric.tutorials.boot.graphql.domain

import de.codecentric.tutorials.boot.graphql.web.dto.Course
import de.codecentric.tutorials.boot.graphql.web.dto.CreateCourse
import de.codecentric.tutorials.boot.graphql.web.dto.UpdateCourse

interface CourseMutationService {

    fun createCourse(input: CreateCourse): Course
    fun updateCourse(input: UpdateCourse): Course
    fun deleteCourse(id: Int): Boolean
}
