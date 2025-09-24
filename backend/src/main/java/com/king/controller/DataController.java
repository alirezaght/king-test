package com.king.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.king.model.DataResponse;
import com.king.service.DataService;

/**
 * REST Controller for data operations
 * Following Single Responsibility Principle - handles only HTTP request/response
 * Following Interface Segregation Principle - depends only on DataService interface
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://frontend:3000"})
public class DataController {
    
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    
    private final DataService dataService;
    
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }
    
    /**
     * Get data with optional filtering, sorting, and pagination
     * 
     * @param page The page number (default: 0)
     * @param size The page size (default: 20)
     * @param search Free text search by name
     * @param status Filter by status value
     * @param sortBy Field to sort by (id, name, createdOn)
     * @param sortOrder Sort order (asc or desc)
     * @return ResponseEntity with data response
     */
    @GetMapping("/data")
    public ResponseEntity<DataResponse> getData(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        
        logger.info("Received request for data: page={}, size={}, search={}, status={}, sortBy={}, sortOrder={}",
                page, size, search, status, sortBy, sortOrder);
        
        try {
            // Validate parameters
            if (page < 0) {
                logger.warn("Invalid page parameter: {}", page);
                return ResponseEntity.badRequest().build();
            }
            
            if (size <= 0 || size > 100) {
                logger.warn("Invalid size parameter: {}", size);
                return ResponseEntity.badRequest().build();
            }
            
            if (sortOrder != null && !sortOrder.equalsIgnoreCase("asc") && !sortOrder.equalsIgnoreCase("desc")) {
                logger.warn("Invalid sortOrder parameter: {}", sortOrder);
                return ResponseEntity.badRequest().build();
            }
            
            if (sortBy != null && !isValidSortBy(sortBy)) {
                logger.warn("Invalid sortBy parameter: {}", sortBy);
                return ResponseEntity.badRequest().build();
            }
            
            DataResponse response = dataService.getData(page, size, search, status, sortBy, sortOrder);
            
            logger.info("Successfully returned data for page {} with {} items", page, response.getData().size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error processing data request: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Validate sortBy parameter
     */
    private boolean isValidSortBy(String sortBy) {
        return sortBy.equals("id") || 
               sortBy.equals("name") || 
               sortBy.equals("createdOn");
    }
}