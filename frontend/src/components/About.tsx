import React from 'react';

const About: React.FC = () => {
  return (
    <div className="about">
      <div className="container">
        <h2 className="page-title">About King</h2>
        <div className="about-content">
          <p>
            King is a full-stack application built with modern technologies:
          </p>
          <ul className="tech-list">
            <li>Frontend: React.js with CSS styling</li>
            <li>Backend: Java Spring Boot with Gradle</li>
            <li>Clean architecture and custom design</li>
          </ul>
          <p>
            This project demonstrates best practices in modern web development
            without relying on UI frameworks, providing a clean and maintainable
            codebase.
          </p>
        </div>
      </div>
    </div>
  );
};

export default About;