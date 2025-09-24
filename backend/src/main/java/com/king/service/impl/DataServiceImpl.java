package com.king.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.king.model.DataItem;
import com.king.model.DataResponse;
import com.king.service.DataFetchService;
import com.king.service.DataProcessingService;
import com.king.service.DataService;

/**
 * Implementation of DataService
 * Following Dependency Inversion Principle - depends on abstractions, not concretions
 * Following Open/Closed Principle - open for extension, closed for modification
 */
@Service
public class DataServiceImpl implements DataService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
    
    private final DataFetchService dataFetchService;
    private final DataProcessingService dataProcessingService;
    
    public DataServiceImpl(DataFetchService dataFetchService, DataProcessingService dataProcessingService) {
        this.dataFetchService = dataFetchService;
        this.dataProcessingService = dataProcessingService;
    }
    
    @Override
    public DataResponse getData(
            Integer page,
            Integer size,
            String search,
            String status,
            String sortBy,
            String sortOrder) {
        
        logger.info("Getting data with parameters: page={}, size={}, search={}, status={}, sortBy={}, sortOrder={}",
                page, size, search, status, sortBy, sortOrder);
        
        try {
            // Fetch all data (will use cache if available)
            List<DataItem> allData = dataFetchService.fetchAllData();
            
            // Process data (filter, sort, paginate)
            DataResponse response = dataProcessingService.processData(
                    allData, page, size, search, status, sortBy, sortOrder);
            
            logger.info("Successfully processed data: totalCount={}, currentPage={}, totalPages={}",
                    response.getTotalCount(), response.getCurrentPage(), response.getTotalPages());
            
            return response;
            
        } catch (Exception e) {
            logger.error("Error processing data request: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process data request: " + e.getMessage(), e);
        }
    }
}