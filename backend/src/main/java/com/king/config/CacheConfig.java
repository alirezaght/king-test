package com.king.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Cache configuration using Caffeine
 * Relies on Spring Boot auto-configuration with properties in application.properties
 */
@Configuration
@EnableCaching
public class CacheConfig {
    // Spring Boot will automatically create a CacheManager based on:
    // spring.cache.type=caffeine
    // spring.cache.cache-names=gcs-data  
    // spring.cache.caffeine.spec=expireAfterWrite=10m,maximumSize=1000
}