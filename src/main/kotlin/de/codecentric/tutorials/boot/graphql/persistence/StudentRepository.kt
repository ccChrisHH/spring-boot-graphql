package de.codecentric.tutorials.boot.graphql.persistence

import de.codecentric.tutorials.boot.graphql.persistence.entities.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<StudentEntity, Int> {
    @Query("select s from StudentEntity s where size(s.courses) <= 2 order by size(s.courses) asc")
    fun findLazyStudents(): List<StudentEntity>

    @Query("select s from StudentEntity s where size(s.courses) >= 5 order by size(s.courses) desc")
    fun findEagerStudents(): List<StudentEntity>
}
