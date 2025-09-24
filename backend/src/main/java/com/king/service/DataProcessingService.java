package com.king.service;

import java.util.List;

import com.king.model.DataItem;
import com.king.model.DataResponse;

/**
 * Interface for data processing operations
 * Following Single Responsibility Principle - handles only data manipulation
 */
public interface DataProcessingService {
    
    /**
     * Processes data with filtering, sorting, and pagination
     * @param data The raw data list
     * @param page Page number (1-based)
     * @param size Page size
     * @param search Search term for name filtering
     * @param status Status filter
     * @param sortBy Field to sort by (id, name, createdOn)
     * @param sortOrder Sort order (asc, desc)
     * @return DataResponse with processed data and pagination info
     */
    DataResponse processData(
            List<DataItem> data,
            Integer page,
            Integer size,
            String search,
            String status,
            String sortBy,
            String sortOrder
    );
}