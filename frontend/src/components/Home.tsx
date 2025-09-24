import React from 'react';

const Home: React.FC = () => {
  return (
    <div className="home">
      <div className="container">
        <section className="hero">
          <h2 className="hero-title">Welcome to King</h2>
          <p className="hero-description">
            A React application with CSS styling and Spring Boot backend
          </p>
          <button className="hero-button">Get Started</button>
        </section>
        
        <section className="features">
          <h3 className="section-title">Features</h3>
          <div className="feature-grid">
            <div className="feature-card">
              <h4>React Frontend</h4>
              <p>Modern React.js application with routing</p>
            </div>
            <div className="feature-card">
              <h4>CSS Styling</h4>
              <p>Custom styling with CSS custom properties</p>
            </div>
            <div className="feature-card">
              <h4>Spring Boot Backend</h4>
              <p>Robust Java backend with Gradle</p>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};

export default Home;