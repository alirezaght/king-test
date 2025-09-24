# King Frontend

A React.js frontend application with TypeScript and CSS styling, built without any UI frameworks.

## Getting Started

### Prerequisites

- Node.js (v16 or higher)
- npm or yarn

### Installation

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Configure environment variables (optional):
   ```bash
   # The .env file is already configured with default values
   # Edit .env if you need to change the backend API URL
   REACT_APP_API_URL=http://localhost:8080
   ```

4. Start the development server:
   ```bash
   npm start
   ```

5. Open [http://localhost:3000](http://localhost:3000) to view the application in your browser.

## Available Scripts

- `npm start` - Runs the app in development mode
- `npm build` - Builds the app for production
- `npm test` - Launches the test runner
- `npm eject` - Ejects from Create React App (one-way operation)

## Environment Variables

The application uses environment variables for configuration. These are defined in the `.env` file:

| Variable | Default Value | Description |
|----------|---------------|-------------|
| `REACT_APP_API_URL` | `http://localhost:8080` | Backend API base URL |


