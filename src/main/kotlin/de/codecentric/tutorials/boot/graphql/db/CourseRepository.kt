package de.codecentric.tutorials.boot.graphql.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Int>
