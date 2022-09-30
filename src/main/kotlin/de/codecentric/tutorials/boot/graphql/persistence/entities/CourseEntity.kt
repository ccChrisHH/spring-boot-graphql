package de.codecentric.tutorials.boot.graphql.persistence.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "courses")
class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    @Column(nullable = false)
    val courseName: String,
    @ManyToMany
    @JoinTable(
        name = "enrollments",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    val students: List<StudentEntity>
)
