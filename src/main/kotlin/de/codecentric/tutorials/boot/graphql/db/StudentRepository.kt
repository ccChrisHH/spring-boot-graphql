package de.codecentric.tutorials.boot.graphql.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Int> {
    @Query("select s from Student s where s.courses.size <= 2 order by s.courses.size asc")
    fun findLazyStudents(): List<Student>

    @Query("select s from Student s where s.courses.size >= 4 order by s.courses.size desc")
    fun findEagerStudents(): List<Student>
}
