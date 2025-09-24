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

- **Frontend**: React.js with CSS styling
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
./gradlew bootRun
```

**Frontend:**
```bash
cd frontend
npm install
npm start
```

## API

- `GET /api/data` - Get paginated data with optional filtering and sorting
- `GET /api/health` - Health check