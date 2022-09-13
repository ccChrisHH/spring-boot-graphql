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
        val students = List(200) {
            val numberOfCourses = (Random().nextInt(1, 5))
            val fakeName = faker.name()
            Student(
                id = null,
                firstName = fakeName.firstName(),
                lastName = fakeName.lastName(),
                age = faker.number().numberBetween(18, 40),
                courses = emptySet()
            )
        }
        studentRepository.saveAll(students)
        val courses = List(15) {
            val numberOfStudents = Random().nextInt(10, 50)
            val fakeCourse = faker.unique().fetchFromYaml("how_i_met_your_mother.high_five")
            Course(
                id = null,
                courseName = fakeCourse,
                students = students.shuffled().take(numberOfStudents).toSet()
            )
        }
        courseRepository.saveAll(courses)
    }
}
