package de.codecentric.tutorials.boot.graphql.domain

import de.codecentric.tutorials.boot.graphql.domain.mapper.toDto
import de.codecentric.tutorials.boot.graphql.persistence.CourseRepository
import de.codecentric.tutorials.boot.graphql.persistence.StudentRepository
import de.codecentric.tutorials.boot.graphql.persistence.entities.CourseEntity
import de.codecentric.tutorials.boot.graphql.web.dto.Student
import org.springframework.stereotype.Service

@Service
class EnrollmentMutationJPAService(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository
) : EnrollmentMutationService {
    override fun enrollStudentInCourse(studentId: Int, courseId: Int): Student {
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

    override fun unEnrollStudentFromCourse(studentId: Int, courseId: Int): Student {
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
