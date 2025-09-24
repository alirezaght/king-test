# King Backend

A Spring Boot backend application with Gradle, providing RESTful APIs.

## Features

- Spring Boot 3.1.5
- Java 17
- Gradle build system
- H2 in-memory database
- JPA/Hibernate for data persistence
- RESTful APIs with CORS support
- Actuator for health monitoring
- Input validation

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle (or use the included Gradle wrapper)

### Installation

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

4. The application will start on [http://localhost:8080](http://localhost:8080)

## Available Endpoints

### Health Endpoints
- `GET /api/health` - Application health status
- `GET /api/info` - Application information

### User Management
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Development
- `GET /h2-console` - H2 database console (development only)

## Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/king/
│   │   │   ├── controller/
│   │   │   │   ├── HealthController.java
│   │   │   │   └── UserController.java
│   │   │   ├── service/
│   │   │   │   ├── HealthService.java
│   │   │   │   └── UserService.java
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java
│   │   │   └── KingApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/king/
│           └── KingApplicationTests.java
├── build.gradle
└── settings.gradle
```

## Database

The application uses H2 in-memory database for development:
- URL: `jdbc:h2:mem:kingdb`
- Username: `sa`
- Password: `password`
- Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Testing

Run tests with:
```bash
./gradlew test
```