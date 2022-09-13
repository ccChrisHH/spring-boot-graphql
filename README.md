# spring-boot-graphql Tutorial

This tutorial project serves as a reference implementaton for demonstrating basic features graphql implemented within a
spring boot application.

This project provides a backend and GraphQL interface for managing courses, students and their enrollments.

A set of random test data will be generated and stored in the in-memory H2 database each time the application is
started.

## Graphiql

To access GraphiQL, a web-based UI for testing GraphQL interfaces, open your browser at `http://localhost:8080/graphiql`
.

To get you started I have added some sample [Queries](QUERIES.MD) and [Mutations](MUTATIONS.MD).

## H2 Console

If you want to inspect the data stored in the in-memory H2 database, open your browser
at `http://localhost:8080/h2-console/`.
Use the following settings to access the database:

```
JDBC URL: jdbc:h2:mem:test
User Name: sa
Password: <blank>
```