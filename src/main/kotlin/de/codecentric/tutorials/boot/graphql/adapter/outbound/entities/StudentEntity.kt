package de.codecentric.tutorials.boot.graphql.adapter.outbound.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class StudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    @Column(nullable = false)
    val firstName: String,
    @Column(nullable = false)
    val lastName: String,
    @Column(nullable = false)
    val age: Int,
    @ManyToMany(mappedBy = "students")
    val courses: List<CourseEntity>
)
