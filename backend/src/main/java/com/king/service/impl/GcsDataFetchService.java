package com.king.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.config.GcsProperties;
import com.king.model.DataItem;
import com.king.model.GcsDataResponse;
import com.king.service.DataFetchService;

/**
 * Implementation of DataFetchService for Google Cloud Storage
 * Following Single Responsibility Principle - only responsible for data fetching
 * Following Dependency Inversion Principle - depends on abstractions
 */
@Service
public class GcsDataFetchService implements DataFetchService {
    
    private static final Logger logger = LoggerFactory.getLogger(GcsDataFetchService.class);
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final GcsProperties gcsProperties;
    
    public GcsDataFetchService(RestTemplate restTemplate, ObjectMapper objectMapper, GcsProperties gcsProperties) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.gcsProperties = gcsProperties;
    }
    
    @Override
    // Cache successful non-empty fetches only; avoid poisoning cache with transient empty responses
    @Cacheable(value = "gcs-data", key = "'all-data'", unless = "#result == null || #result.isEmpty()")
    public List<DataItem> fetchAllData() {
        try {
            logger.info("Fetching data from GCS: {}", gcsProperties.getUrl());
            
            // Fetch raw JSON string from GCS
            String jsonResponse = restTemplate.getForObject(gcsProperties.getUrl(), String.class);
            
            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                logger.warn("Empty response from GCS data source");
                return List.of();
            }
            
            // Parse JSON to GcsDataResponse wrapper
            GcsDataResponse gcsResponse = objectMapper.readValue(jsonResponse, GcsDataResponse.class);
            
            if (gcsResponse.getOutput() == null) {
                logger.warn("No output data in GCS response");
                return List.of();
            }
            
            List<DataItem> dataItems = gcsResponse.getOutput();
            
            logger.info("Successfully fetched {} data items from GCS", dataItems.size());
            return dataItems;
            
        } catch (Exception e) {
            logger.error("Error fetching data from GCS: {}", e.getMessage(), e);
            // Return empty list to maintain service availability
            return List.of();
        }
    }
}