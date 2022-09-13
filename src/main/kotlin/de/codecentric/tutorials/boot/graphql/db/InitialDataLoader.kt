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
        val courses = List(10) {
            val fakeCourse = faker.unique().fetchFromYaml("how_i_met_your_mother.high_five")
            Course(
                id = null,
                courseName = fakeCourse,
                students = emptySet()
            )
        }
        val savedCourses = courseRepository.saveAll(courses)
        val students = List(100) {
            val numberOfCourses = (Random().nextInt(1, 5))
            val fakeName = faker.name()
            Student(
                id = null,
                firstName = fakeName.firstName(),
                lastName = fakeName.lastName(),
                age = faker.number().numberBetween(18, 40),
                courses = (savedCourses).shuffled().take(numberOfCourses).toSet()
            )
        }
        studentRepository.saveAll(students)
    }
}
