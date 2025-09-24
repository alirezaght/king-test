# End-to-End Tests for King Application

This directory contains Playwright end-to-end tests for the King data table application.

## Setup

1. Install dependencies:
```bash
npm install
```

2. Install Playwright browsers:
```bash
npm run install-browsers
```

## Configuration

The tests use environment variables for configuration. Create or modify the `.env` file:

```bash
# Frontend URL - defaults to localhost:3000 if not set
FRONTEND_URL=http://localhost:3000

```

## Running Tests

### Prerequisites
Make sure both the frontend and backend applications are running:

```bash
cd ../
docker compose up --build
```

### Run Tests

```bash
# Run all tests (headless)
npm test

# Run tests with browser UI visible
npm run test:headed

# Run tests with Playwright UI mode
npm run test:ui

# Debug tests step by step
npm run test:debug

# View test report after running
npm run report
```

