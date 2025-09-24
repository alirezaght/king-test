package com.king.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public Map<String, String> getApplicationInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "King Backend");
        info.put("description", "Spring Boot backend for King application");
        info.put("version", "1.0.0");
        info.put("java.version", System.getProperty("java.version"));
        info.put("spring.version", org.springframework.core.SpringVersion.getVersion());
        return info;
    }
}