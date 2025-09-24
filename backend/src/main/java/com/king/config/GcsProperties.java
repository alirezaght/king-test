package com.king.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for GCS data source
 */
@Configuration
@ConfigurationProperties(prefix = "gcs.data")
public class GcsProperties {
    
    private String url;
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}