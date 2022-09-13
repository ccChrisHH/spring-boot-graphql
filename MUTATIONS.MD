# Sample Mutations

## create a student

```graphql
mutation CreateStudent($input: StudentInput) {
    createStudent(input: $input) {
        id
        firstName
        lastName
        age
        courses {
            courseName
        }
    }
}
```

## update a student

```graphql
mutation UpdateStudent($input: StudentUpdate) {
    updateStudent(input: $input) {
        id
        firstName
        lastName
        age
        courses {
            courseName
        }
    }
}
```

## delete a student

```graphql
mutation DeleteStudent($input: Int!) {
    deleteStudent(id: $input)
}
```

## create a course

```graphql
mutation CreateCourse($input: CourseInput) {
    createCourse(input: $input) {
        id
        courseName
    }
}
```

## update a course

```graphql
mutation UpdateCourse($input: CourseUpdate) {
    updateCourse(input: $input) {
        id
        courseName
    }
}
```

## delete a course

```graphql
mutation DeleteCourse($input: Int!) {
    deleteCourse(id: $input)
}
```

## enroll a student in a course

```graphql
mutation EnrollStudent($studentId: Int!, $courseId: Int!) {
    enrollStudentInCourse(studentId: $studentId, courseId: $courseId) {
        id
        firstName
        lastName
        age
        courses {
            courseName
        }
    }
}
```

## unenroll a student in a course

```graphql
mutation UnEnrollStudent($studentId: Int!, $courseId: Int!) {
    unEnrollStudentFromCourse(studentId: $studentId, courseId: $courseId) {
        id
        firstName
        lastName
        age
        courses {
            courseName
        }
    }
}
```