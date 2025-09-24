package com.king.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Custom deserializer for delta field which can be:
 * - Number (double/integer)
 * - String representation of number
 * - Empty string
 * - null
 */
public class DeltaDeserializer extends JsonDeserializer<Double> {
    
    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        
        if (node.isNull()) {
            return null;
        }
        
        if (node.isNumber()) {
            return node.asDouble();
        }
        
        if (node.isTextual()) {
            String deltaStr = node.asText().trim();
            if (deltaStr.isEmpty()) {
                return null;
            }
            
            try {
                return Double.parseDouble(deltaStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        
        return null;
    }
}