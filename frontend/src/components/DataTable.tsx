import React, { useState, useMemo, useEffect } from 'react';
import { useTableData } from '../hooks/useTableData';
import { ApiParams } from '../services/api';

export interface DataItem {
  id: number;
  name: string;
  status: string;
  description: string;
  delta: number;
  createdOn: string;
}

const DataTable: React.FC = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [statusFilter, setStatusFilter] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [sortField, setSortField] = useState<'id' | 'name' | 'createdOn'>('id');
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');
  const [debouncedSearchTerm, setDebouncedSearchTerm] = useState('');
  const itemsPerPage = 20;

  // Debounce search term to avoid too many API calls
  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedSearchTerm(searchTerm);
      setCurrentPage(1); // Reset to first page when search changes
    }, 500);

    return () => clearTimeout(timer);
  }, [searchTerm]);

  // Prepare API parameters
  const apiParams: ApiParams = useMemo(() => ({
    page: currentPage,
    size: itemsPerPage,
    search: debouncedSearchTerm || undefined,
    status: statusFilter || undefined,
    sortBy: sortField,
    sortOrder: sortOrder,
  }), [currentPage, debouncedSearchTerm, statusFilter, sortField, sortOrder]);

  // Fetch data using React Query
  const { data: apiResponse, isLoading, error, isError } = useTableData(apiParams);

  // Get unique status values for filter dropdown
  const statusOptions = useMemo(() => {
    if (!apiResponse?.data) return [];
    const statusSet = new Set(apiResponse.data.map((item: DataItem) => item.status));
    const statuses = Array.from(statusSet) as string[];
    return statuses.sort();
  }, [apiResponse?.data]);

  // Handle sorting
  const handleSort = (field: 'id' | 'name' | 'createdOn') => {
    if (sortField === field) {
      setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    } else {
      setSortField(field);
      setSortOrder('asc');
    }
    setCurrentPage(1); // Reset to first page when sorting
  };

  // Handle search
  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  // Handle status filter
  const handleStatusFilter = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setStatusFilter(e.target.value);
    setCurrentPage(1); // Reset to first page when filtering
  };

  // Format date for display
  const formatDate = (dateString: string) => {
    return new Date(dateString).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  // Generate page numbers for pagination
  const getPageNumbers = () => {
    if (!apiResponse) return [];
    
    const pages = [];
    const maxVisiblePages = 5;
    const totalPages = apiResponse.totalPages;
    let startPage = Math.max(1, currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

    if (endPage - startPage + 1 < maxVisiblePages) {
      startPage = Math.max(1, endPage - maxVisiblePages + 1);
    }

    for (let i = startPage; i <= endPage; i++) {
      pages.push(i);
    }
    return pages;
  };

  if (isError) {
    return (
      <div className="data-table">
        <div className="error">
          <h3>Error Loading Data</h3>
          <p>{error?.message || 'An unknown error occurred'}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="data-table">
      {/* Controls */}
      <div className="table-controls">
        <div className="search-control">
          <label htmlFor="search">Search by name:</label>
          <input
            type="text"
            id="search"
            value={searchTerm}
            onChange={handleSearch}
            placeholder="Enter name to search..."
            className="search-input"
          />
        </div>
        
        <div className="filter-control">
          <label htmlFor="status-filter">Filter by status:</label>
          <select
            id="status-filter"
            value={statusFilter}
            onChange={handleStatusFilter}
            className="status-filter"
          >
            <option value="">All statuses</option>
            {statusOptions.map(status => (
              <option key={status} value={status}>{status}</option>
            ))}
          </select>
        </div>
      </div>

      {/* Results info */}
      <div className="table-info">
        {apiResponse ? (
          <>
            Showing {apiResponse.data.length} of {apiResponse.totalCount} entries
            {searchTerm && ` (filtered by "${searchTerm}")`}
            {statusFilter && ` (filtered by status "${statusFilter}")`}
          </>
        ) : (
          'Loading...'
        )}
      </div>

      {/* Table */}
      <div className="table-container">
        <table className="data-table-grid">
          <thead>
            <tr>
              <th 
                className={`sortable ${sortField === 'id' ? `sorted-${sortOrder}` : ''}`}
                onClick={() => handleSort('id')}
              >
                ID {sortField === 'id' && (sortOrder === 'asc' ? '↑' : '↓')}
              </th>
              <th 
                className={`sortable ${sortField === 'name' ? `sorted-${sortOrder}` : ''}`}
                onClick={() => handleSort('name')}
              >
                Name {sortField === 'name' && (sortOrder === 'asc' ? '↑' : '↓')}
              </th>
              <th>Status</th>
              <th>Description</th>
              <th>Delta</th>
              <th 
                className={`sortable ${sortField === 'createdOn' ? `sorted-${sortOrder}` : ''}`}
                onClick={() => handleSort('createdOn')}
              >
                Created On {sortField === 'createdOn' && (sortOrder === 'asc' ? '↑' : '↓')}
              </th>
            </tr>
          </thead>
          <tbody>
            {isLoading ? (
              <tr>
                <td colSpan={6} className="loading-cell">
                  Loading data...
                </td>
              </tr>
            ) : apiResponse?.data.length === 0 ? (
              <tr>
                <td colSpan={6} className="no-data-cell">
                  No data found
                </td>
              </tr>
            ) : (
              apiResponse?.data.map((item: DataItem) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.name}</td>
                  <td>
                    <span className={`status-badge status-${item.status.toLowerCase().replace(/\s+/g, '-')}`}>
                      {item.status}
                    </span>
                  </td>
                  <td className="description-cell">{item.description}</td>
                  <td className="delta-cell">{item.delta}</td>
                  <td>{formatDate(item.createdOn)}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {/* Pagination */}
      {apiResponse && apiResponse.totalPages > 1 && (
        <div className="pagination">
          <button 
            onClick={() => setCurrentPage(1)} 
            disabled={currentPage === 1 || isLoading}
            className="pagination-btn"
          >
            First
          </button>
          <button 
            onClick={() => setCurrentPage(Math.max(1, currentPage - 1))} 
            disabled={!apiResponse.hasPrevious || isLoading}
            className="pagination-btn"
          >
            Previous
          </button>
          
          {getPageNumbers().map(page => (
            <button
              key={page}
              onClick={() => setCurrentPage(page)}
              disabled={isLoading}
              className={`pagination-btn ${currentPage === page ? 'active' : ''}`}
            >
              {page}
            </button>
          ))}
          
          <button 
            onClick={() => setCurrentPage(Math.min(apiResponse.totalPages, currentPage + 1))} 
            disabled={!apiResponse.hasNext || isLoading}
            className="pagination-btn"
          >
            Next
          </button>
          <button 
            onClick={() => setCurrentPage(apiResponse.totalPages)} 
            disabled={currentPage === apiResponse.totalPages || isLoading}
            className="pagination-btn"
          >
            Last
          </button>
        </div>
      )}
    </div>
  );
};

export default DataTable;