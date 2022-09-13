package de.codecentric.tutorials.boot.graphql.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int> {
    fun findCoursesByStudentsContaining(student: Student): List<Course>

    @Query("select c from Course c where size(c.students) <= 15 order by size(c.students) asc")
    fun findUnpopularCourses(): List<Course>

    @Query("select c from Course c where size(c.students) >=30 order by size(c.students) desc")
    fun findPopularCourses(): List<Course>
}
