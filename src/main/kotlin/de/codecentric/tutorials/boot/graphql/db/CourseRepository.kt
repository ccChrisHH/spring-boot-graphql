package de.codecentric.tutorials.boot.graphql.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int> {
    fun findCoursesByStudentsContaining(student: Student): List<Course>

    @Query("select c from Course c where c.students.size <= 15 order by c.students.size asc")
    fun findUnpopularCourses(): List<Course>

    @Query("select c from Course c where c.students.size >=30 order by c.students.size desc")
    fun findPopularCourses(): List<Course>
}
