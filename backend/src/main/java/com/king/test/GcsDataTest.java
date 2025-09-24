package com.king.test;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.king.model.GcsDataResponse;

public class GcsDataTest {
    
    public static void main(String[] args) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            
            String url = "https://storage.googleapis.com/king-airnd-recruitment-sandbox-data/data.json";
            
            System.out.println("Fetching data from: " + url);
            
            String jsonResponse = restTemplate.getForObject(url, String.class);
            System.out.println("JSON response length: " + (jsonResponse != null ? jsonResponse.length() : "null"));
            
            if (jsonResponse != null) {
                GcsDataResponse gcsResponse = objectMapper.readValue(jsonResponse, GcsDataResponse.class);
                System.out.println("Parsed successfully!");
                System.out.println("Output size: " + (gcsResponse.getOutput() != null ? gcsResponse.getOutput().size() : "null"));
                
                if (gcsResponse.getOutput() != null && !gcsResponse.getOutput().isEmpty()) {
                    System.out.println("First item: " + gcsResponse.getOutput().get(0));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}