package de.codecentric.tutorials.boot.graphql.db

import net.datafaker.Faker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.util.Random

@Component
class InitialDataLoader(
    private val courseRepository: CourseRepository,
    private val studentRepository: StudentRepository
) : ApplicationListener<ApplicationReadyEvent> {

    private val faker = Faker()

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val students = createStudents()
        studentRepository.saveAll(students)
        courseRepository.saveAll(createCoursesAndEnrollments(students))
    }

    private fun createStudents(): List<StudentEntity> {
        return List(200) {
            val fakeName = faker.name()
            StudentEntity(
                id = null,
                firstName = fakeName.firstName(),
                lastName = fakeName.lastName(),
                age = faker.number().numberBetween(18, 40),
                courses = emptyList()
            )
        }
    }

    private fun createCoursesAndEnrollments(studentEntities: List<StudentEntity>): List<CourseEntity> {
        return List(15) {
            val numberOfStudents = Random().nextInt(10, 50)
            val fakeCourse = faker.unique().fetchFromYaml("how_i_met_your_mother.high_five")
            CourseEntity(
                id = null,
                courseName = fakeCourse,
                students = studentEntities.shuffled().take(numberOfStudents)
            )
        }
    }
}
