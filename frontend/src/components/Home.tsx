import React from 'react';
import DataTable from './DataTable';

const Home: React.FC = () => {
  return (
    <div className="home">
      <div className="container">
        <section className="table-section">
          <h2 className="section-title">Data Table</h2>
          <DataTable />
        </section>
      </div>
    </div>
  );
};

export default Home;