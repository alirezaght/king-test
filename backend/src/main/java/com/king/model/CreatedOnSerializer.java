package com.king.model;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom serializer for createdOn field to output ISO string format
 */
public class CreatedOnSerializer extends JsonSerializer<OffsetDateTime> {
    
    @Override
    public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            // Normalize to UTC when serializing
            gen.writeString(value.withOffsetSameInstant(ZoneOffset.UTC).toInstant().toString());
        }
    }
}