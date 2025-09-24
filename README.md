# King - Technical Test

This repository contains a full-stack application for King's technical assessment.

## Task

Retrieve data from Google Cloud Storage (GCS) and display it in a table with the following features:

**Table Columns:**
- id, name, status, description, delta, createdOn

**Features:**
- Free text search by name
- Filter by status value
- Pagination (20 entries per page)
- Row ordering on id, name, createdOn

**Data Source:** https://storage.googleapis.com/king-airnd-recruitment-sandbox-data/data.json

## Architecture

- **Frontend**: React.js with TypeScript and CSS styling
- **Backend**: Spring Boot with data processing and GCS integration
- All filtering, pagination, and data manipulation handled by backend

## Quick Start

### Using Docker (Recommended)

```bash
# Build and run both services
docker-compose up --build

# Frontend: http://localhost:3000
# Backend: http://localhost:8080
```

### Manual Setup

**Backend:**
```bash
cd backend
gradle wrapper --gradle-version 8.4
./gradlew bootRun
```

**Frontend:**
```bash
cd frontend
npm install
npm start
```

### Configuration

**Frontend Environment Variables:**
The frontend uses environment variables defined in `frontend/.env`:
- `REACT_APP_API_URL=http://localhost:8080` - Backend API URL

**Backend Configuration:**
Backend configuration is in `backend/src/main/resources/application.properties`:
- GCS data source URL
- Cache configuration (10-minute TTL)
- Server port and logging

## API

- `GET /api/data` - Get paginated data with optional filtering and sorting
- `GET /api/health` - Health check

## Testing Strategy

The project follows a testing strategy with multiple layers of test coverage:

### Backend Tests (`backend/src/test/`)

**Unit & Integration Tests:**

**Run Backend Tests:**
```bash
cd backend
./gradlew test
```

### End-to-End Tests (`e2e/`)


**Setup E2E Tests:**
```bash
cd e2e
npm install
npm run install-browsers
```

**Run E2E Tests:**
```bash
# Ensure both frontend and backend are running first
cd backend && ./gradlew bootRun &
cd frontend && npm start &

# Then run E2E tests
cd e2e
npm test              # Headless mode
npm run test:headed   # With browser UI
npm run test:ui       # Playwright UI mode
```

