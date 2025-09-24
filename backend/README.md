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

### Data API
- `GET /api/data` - Retrieve paginated data with filtering, searching, and sorting

#### Parameters
| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `page` | Integer | 1 | Page number (1-based) |
| `size` | Integer | 20 | Number of items per page (max 100) |
| `search` | String | - | Free text search by name |
| `status` | String | - | Filter by status (COMPLETED, CANCELED, ERROR) |
| `sortBy` | String | id | Sort field (id, name, createdOn) |
| `sortOrder` | String | asc | Sort order (asc, desc) |

#### Response
```json
{
  "data": [
    {
      "id": 123,
      "name": "Item name",
      "status": "COMPLETED",
      "description": "Item description",
      "delta": 15.75,
      "createdOn": "2023-11-15T10:30:00"
    }
  ],
  "totalCount": 500,
  "currentPage": 0,
  "totalPages": 25,
  "hasNext": true,
  "hasPrevious": false
}
```

### Health Endpoints
- `GET /api/health` - Application health status


## Testing

Run tests with:
```bash
./gradlew test
```
