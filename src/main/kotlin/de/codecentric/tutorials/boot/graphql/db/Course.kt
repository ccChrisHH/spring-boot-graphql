package de.codecentric.tutorials.boot.graphql.db

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @Column(nullable = false)
    val courseName: String,
    @ManyToMany(mappedBy = "courses")
    val students: Set<Student>
)
