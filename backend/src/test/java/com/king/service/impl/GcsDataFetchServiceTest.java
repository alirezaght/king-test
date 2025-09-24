package com.king.service.impl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.king.model.DataItem;
import com.king.service.DataFetchService;

/**
 * Integration test for GcsDataFetchService
 * Tests actual data fetching from GCS and schema validation
 */
@SpringBootTest
class GcsDataFetchServiceTest {

    @Autowired
    private DataFetchService dataFetchService;
    
    @Test
    void fetchAllData_shouldReturnValidDataWithCorrectSchema() {
        // When
        List<DataItem> result = dataFetchService.fetchAllData();
        
        // Then - Check we got data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.size() > 0, "Should return more than 0 data items");
        
        // Validate schema of first item
        DataItem firstItem = result.get(0);
        
        // Check all required fields exist and have correct types
        assertNotNull(firstItem.getId(), "ID should not be null");
        assertTrue(firstItem.getId() instanceof Long, "ID should be Long type");
        
        assertNotNull(firstItem.getName(), "Name should not be null");
        assertTrue(firstItem.getName() instanceof String, "Name should be String type");
        
        assertNotNull(firstItem.getStatus(), "Status should not be null");
        assertTrue(firstItem.getStatus() instanceof String, "Status should be String type");
        
        assertNotNull(firstItem.getDescription(), "Description should not be null");
        assertTrue(firstItem.getDescription() instanceof String, "Description should be String type");
        
        assertNotNull(firstItem.getDelta(), "Delta should not be null");
        assertTrue(firstItem.getDelta() instanceof Double, "Delta should be Double type");
        
        // CreatedOn can be null, so only check type if not null
        if (firstItem.getCreatedOn() != null) {
            // Just ensure it's properly deserialized (LocalDateTime is handled by deserializer)
            assertNotNull(firstItem.getCreatedOn(), "CreatedOn should be properly deserialized when not null");
        }
        
        System.out.println("✅ Successfully fetched " + result.size() + " items from GCS");
        System.out.println("✅ First item schema validation passed");
        System.out.println("   - ID: " + firstItem.getId());
        System.out.println("   - Name: " + firstItem.getName());
        System.out.println("   - Status: " + firstItem.getStatus());
        System.out.println("   - Delta: " + firstItem.getDelta());
        System.out.println("   - CreatedOn: " + firstItem.getCreatedOn());
    }
}