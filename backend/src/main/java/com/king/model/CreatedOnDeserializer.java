package com.king.model;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
public class CreatedOnDeserializer extends JsonDeserializer<OffsetDateTime> {
    
    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
        }
        
        if (node.isTextual()) {
            String dateStr = node.asText().trim();
            if (dateStr.isEmpty()) {
                return null;
            }
            
            try {
                // Try parsing as ISO string
                // Prefer parsing as OffsetDateTime if zone present
                try {
                    return OffsetDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME).withOffsetSameInstant(ZoneOffset.UTC);
                } catch (DateTimeParseException inner) {
                    // Fallback: treat as local date-time without zone and assume UTC
                    LocalDateTime ldt = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
                    return ldt.atOffset(ZoneOffset.UTC);
                }
            } catch (DateTimeParseException e) {
                // Try parsing as Unix timestamp in string format
                try {
                    long timestamp = Long.parseLong(dateStr);
                    if (timestamp == 0) {
                        return null;
                    }
                    return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
                } catch (NumberFormatException ex) {
                    // If all parsing fails, return null
                    return null;
                }
            }
        }
        
        return null;
    }
}