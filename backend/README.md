# King Backend

A Spring Boot backend application with Gradle, providing RESTful APIs.

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


## Testing

Run tests with:
```bash
./gradlew test
```