package com.king.service;

import com.king.model.DataResponse;

/**
 * Main service interface for data operations
 * Following Dependency Inversion Principle - depends on abstractions
 */
public interface DataService {
    
    /**
     * Gets processed data with filtering, sorting, and pagination
     * @param page Page number (1-based)
     * @param size Page size
     * @param search Search term for name filtering
     * @param status Status filter
     * @param sortBy Field to sort by (id, name, createdOn)
     * @param sortOrder Sort order (asc, desc)
     * @return DataResponse with processed data and pagination info
     */
    DataResponse getData(
            Integer page,
            Integer size,
            String search,
            String status,
            String sortBy,
            String sortOrder
    );
}