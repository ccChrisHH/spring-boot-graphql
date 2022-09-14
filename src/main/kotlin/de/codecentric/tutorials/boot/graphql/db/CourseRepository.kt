package de.codecentric.tutorials.boot.graphql.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<CourseEntity, Int> {
    fun findCoursesByStudentsContaining(studentEntity: StudentEntity): List<CourseEntity>

    @Query("select c from CourseEntity c where size(c.students) <= 15 order by size(c.students) asc")
    fun findUnpopularCourses(): List<CourseEntity>

    @Query("select c from CourseEntity c where size(c.students) >=30 order by size(c.students) desc")
    fun findPopularCourses(): List<CourseEntity>
}
