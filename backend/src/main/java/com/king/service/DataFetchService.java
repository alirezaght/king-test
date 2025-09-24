package com.king.service;

import java.util.List;

import com.king.model.DataItem;

/**
 * Interface for fetching raw data from external sources
 * Following Interface Segregation Principle - focused on single responsibility
 */
public interface DataFetchService {
    
    /**
     * Fetches all data items from the external data source
     * @return List of DataItem objects
     * @throws RuntimeException if data cannot be fetched
     */
    List<DataItem> fetchAllData();
}