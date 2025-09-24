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

3. Start the development server:
   ```bash
   npm start
   ```

4. Open [http://localhost:3000](http://localhost:3000) to view the application in your browser.

## Available Scripts

- `npm start` - Runs the app in development mode
- `npm build` - Builds the app for production
- `npm test` - Launches the test runner
- `npm eject` - Ejects from Create React App (one-way operation)

## Project Structure

```
frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Header.tsx
│   │   ├── Home.tsx
│   │   └── About.tsx
│   ├── styles/
│   │   └── main.css
│   ├── App.tsx
│   ├── index.tsx
│   └── react-app-env.d.ts
├── tsconfig.json
└── package.json
```

## Styling

The application uses CSS with:
- CSS custom properties (variables) for colors, spacing, and breakpoints
- Responsive design patterns
- Clean and modern aesthetic
- No external UI frameworks

## TypeScript

The project is built with TypeScript for:
- Type safety and better development experience
- Enhanced IDE support with autocompletion
- Better refactoring capabilities
- Compile-time error detection

