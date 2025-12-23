# Backend Ecommerce

Backend application built with Java 21, Spring Boot, and PostgreSQL (Supabase).

## Requirements

- Java 21
- Maven 3.6+
- PostgreSQL database (pgAdmin)

## Configuration

1. Update the database configuration in `src/main/resources/application.properties`, change them into yours:
   ```
   spring.datasource.url=YOUR_POSTGRES_URL_CONNECTION
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```
2. If you run the project in VS Code, then install these extension:
   ```
   Java
   Java Language Support
   Extension Pack for Java
   Language Support for Java(TM) by Red Hat
   Lombok Annotations Support for VS Code
   Maven for Java
   Spring Boot Dashboard
   Spring Boot Extension Pack
   Spring Boot Tools
   Spring Initializr Java Support
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

