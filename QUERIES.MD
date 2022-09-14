# Sample Queries

## query all students

```graphql
query AllStudents {
    allStudents {
        id
        firstName
        lastName
        age
    }
}
```

## query all courses

```graphql
query AllCourses {
    allCourses {
        id
        courseName
    }
}
```

## query eager students

```graphql
query EagerStudents {
    eagerStudents {
        id
        firstName
        lastName
        age
        numberOfCourses
    }
}
```

## query lazy students

```graphql
query LazyStudents {
    lazyStudents {
        id
        firstName
        lastName
        age
        numberOfCourses
    }
}
```

## query popular courses

```graphql
query PopularCourses{
    popularCourses{
        id
        courseName
        numberOfStudents
    }
}
```

## query unpopular courses

```graphql
query UnpopularCourses{
    unpopularCourses{
        id
        courseName
        numberOfStudents
    }
}
```