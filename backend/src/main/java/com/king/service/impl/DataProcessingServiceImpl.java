package com.king.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.king.model.DataItem;
import com.king.model.DataResponse;
import com.king.service.DataProcessingService;

/**
 * Implementation of DataProcessingService
 * Following Single Responsibility Principle - only responsible for data processing
 */
@Service
public class DataProcessingServiceImpl implements DataProcessingService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataProcessingServiceImpl.class);
    
    @Override
    public DataResponse processData(
            List<DataItem> data,
            Integer page,
            Integer size,
            String search,
            String status,
            String sortBy,
            String sortOrder) {
        
        logger.debug("Processing data with parameters: page={}, size={}, search={}, status={}, sortBy={}, sortOrder={}",
                page, size, search, status, sortBy, sortOrder);
        
        // Set defaults
        page = (page != null && page > 0) ? page : 1;
        size = (size != null && size > 0) ? size : 20;
        sortBy = StringUtils.hasText(sortBy) ? sortBy : "id";
        sortOrder = StringUtils.hasText(sortOrder) ? sortOrder : "asc";
        
        // Filter data
        List<DataItem> filteredData = data.stream()
                .filter(item -> matchesSearch(item, search))
                .filter(item -> matchesStatus(item, status))
                .collect(Collectors.toList());
        
        // Sort data
        List<DataItem> sortedData = sortData(filteredData, sortBy, sortOrder);
        
        // Calculate pagination
        long totalCount = sortedData.size();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, sortedData.size());
        
        // Get page data
        List<DataItem> pageData = sortedData.subList(startIndex, endIndex);
        
        // Create response
        DataResponse response = new DataResponse(
                pageData,
                totalCount,
                page,
                totalPages,
                page < totalPages,
                page > 1
        );
        
        logger.debug("Processed data: totalCount={}, totalPages={}, currentPage={}", 
                totalCount, totalPages, page);
        
        return response;
    }
    
    private boolean matchesSearch(DataItem item, String search) {
        if (!StringUtils.hasText(search)) {
            return true;
        }
        return item.getName() != null && 
               item.getName().toLowerCase().contains(search.toLowerCase());
    }
    
    private boolean matchesStatus(DataItem item, String status) {
        if (!StringUtils.hasText(status)) {
            return true;
        }
        return status.equals(item.getStatus());
    }
    
    private List<DataItem> sortData(List<DataItem> data, String sortBy, String sortOrder) {
        Comparator<DataItem> comparator = getComparator(sortBy, sortOrder);
        
        return data.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    
    private Comparator<DataItem> getComparator(String sortBy, String sortOrder) {
        boolean isDescending = "desc".equalsIgnoreCase(sortOrder);
        
        switch (sortBy.toLowerCase()) {
            case "name":
                Comparator<DataItem> nameComparator = Comparator.comparing(DataItem::getName, 
                        isDescending ? Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER.reversed()) 
                                     : Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
                return nameComparator;
                
            case "createdon":
                Comparator<DataItem> dateComparator = Comparator.comparing(DataItem::getCreatedOn,
                        isDescending ? Comparator.nullsLast(Comparator.<LocalDateTime>naturalOrder().reversed())
                                     : Comparator.nullsFirst(Comparator.naturalOrder()));
                return dateComparator;
                
            case "id":
            default:
                Comparator<DataItem> idComparator = Comparator.comparing(DataItem::getId, 
                        isDescending ? Comparator.nullsLast(Comparator.<Long>naturalOrder().reversed())
                                     : Comparator.nullsFirst(Comparator.naturalOrder()));
                return idComparator;
        }
    }
}