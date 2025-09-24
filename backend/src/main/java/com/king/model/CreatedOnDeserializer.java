package com.king.model;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Custom deserializer for createdOn field which can be:
 * - Unix timestamp (long)
 * - ISO string
 * - Empty string
 * - null
 */
public class CreatedOnDeserializer extends JsonDeserializer<LocalDateTime> {
    
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        
        if (node.isNull() || node.asText().trim().isEmpty()) {
            return null;
        }
        
        if (node.isNumber()) {
            // Handle Unix timestamp (milliseconds)
            long timestamp = node.asLong();
            if (timestamp == 0) {
                return null;
            }
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }
        
        if (node.isTextual()) {
            String dateStr = node.asText().trim();
            if (dateStr.isEmpty()) {
                return null;
            }
            
            try {
                // Try parsing as ISO string
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
            } catch (DateTimeParseException e) {
                // Try parsing as Unix timestamp in string format
                try {
                    long timestamp = Long.parseLong(dateStr);
                    if (timestamp == 0) {
                        return null;
                    }
                    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
                } catch (NumberFormatException ex) {
                    // If all parsing fails, return null
                    return null;
                }
            }
        }
        
        return null;
    }
}