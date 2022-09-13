package de.codecentric.tutorials.boot.graphql.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Int> {
    @Query("select s from Student s where size(s.courses) <= 2 order by size(s.courses) asc")
    fun findLazyStudents(): List<Student>

    @Query("select s from Student s where size(s.courses) >= 5 order by size(s.courses) desc")
    fun findEagerStudents(): List<Student>
}
