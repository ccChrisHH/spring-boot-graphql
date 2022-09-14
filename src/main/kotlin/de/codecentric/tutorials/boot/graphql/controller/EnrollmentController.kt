package de.codecentric.tutorials.boot.graphql.controller

import de.codecentric.tutorials.boot.graphql.db.CourseEntity
import de.codecentric.tutorials.boot.graphql.db.CourseRepository
import de.codecentric.tutorials.boot.graphql.db.StudentEntity
import de.codecentric.tutorials.boot.graphql.db.StudentRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class EnrollmentController(
    val studentRepository: StudentRepository,
    val courseRepository: CourseRepository
) {

    @MutationMapping
    fun enrollStudentInCourse(@Argument studentId: Int, @Argument courseId: Int): StudentEntity {
        val courseRecord = courseRepository.getReferenceById(courseId)
        val studentRecord = studentRepository.getReferenceById(studentId)
        courseRepository.save(
            CourseEntity(
                id = courseRecord.id,
                courseName = courseRecord.courseName,
                students = courseRecord.students.plus(studentRecord)
            )
        )
        return studentRecord
    }

    @MutationMapping
    fun unEnrollStudentFromCourse(@Argument studentId: Int, @Argument courseId: Int): StudentEntity {
        val courseRecord = courseRepository.getReferenceById(courseId)
        val studentRecord = studentRepository.getReferenceById(studentId)
        courseRepository.save(
            CourseEntity(
                id = courseRecord.id,
                courseName = courseRecord.courseName,
                students = courseRecord.students.minus(studentRecord)
            )
        )
        return studentRecord
    }
}
