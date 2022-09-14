package de.codecentric.tutorials.boot.graphql.adapter.inbound

import de.codecentric.tutorials.boot.graphql.adapter.inbound.dto.Student
import de.codecentric.tutorials.boot.graphql.adapter.inbound.mapper.toDto
import de.codecentric.tutorials.boot.graphql.adapter.outbound.CourseRepository
import de.codecentric.tutorials.boot.graphql.adapter.outbound.StudentRepository
import de.codecentric.tutorials.boot.graphql.adapter.outbound.entities.CourseEntity
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class EnrollmentMutationController(
    val studentRepository: StudentRepository,
    val courseRepository: CourseRepository
) {
    @MutationMapping
    fun enrollStudentInCourse(@Argument studentId: Int, @Argument courseId: Int): Student {
        val courseRecord = courseRepository.getReferenceById(courseId)
        val studentRecord = studentRepository.getReferenceById(studentId)
        courseRepository.save(
            CourseEntity(
                id = courseRecord.id,
                courseName = courseRecord.courseName,
                students = courseRecord.students.plus(studentRecord)
            )
        )
        return studentRecord.toDto()
    }

    @MutationMapping
    fun unEnrollStudentFromCourse(@Argument studentId: Int, @Argument courseId: Int): Student {
        val courseRecord = courseRepository.getReferenceById(courseId)
        val studentRecord = studentRepository.getReferenceById(studentId)
        courseRepository.save(
            CourseEntity(
                id = courseRecord.id,
                courseName = courseRecord.courseName,
                students = courseRecord.students.minus(studentRecord)
            )
        )
        return studentRecord.toDto()
    }
}
