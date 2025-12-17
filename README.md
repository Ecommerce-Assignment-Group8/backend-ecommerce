# Backend Ecommerce

Backend application built with Java 21, Spring Boot, and PostgreSQL (Supabase).

## Requirements

- Java 21
- Maven 3.6+
- PostgreSQL database (Supabase)

## Configuration

1. Update the database password in `src/main/resources/application.properties`:
   ```
   spring.datasource.password=YOUR_PASSWORD
   ```

## Running the Application

```bash
mvn spring-boot:run
```

Or build and run:

```bash
mvn clean package
java -jar target/backend-ecommerce-1.0.0.jar
```

The application will start on `http://localhost:8080`

## Project Structure

```
backend-ecommerce/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/backend/
│   │   │       └── BackendEcommerceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## Technologies

- Java 21
- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Hibernate

