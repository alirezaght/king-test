package com.king.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DataItem {
    
    private Long id;
    private String name;
    private String status;
    private String description;
    
    private Double delta;
    
    @JsonProperty("createdOn")
    @JsonDeserialize(using = CreatedOnDeserializer.class)
    @JsonSerialize(using = CreatedOnSerializer.class)
    private LocalDateTime createdOn;
    
    // Default constructor
    public DataItem() {}
    
    // Constructor with parameters
    public DataItem(Long id, String name, String status, String description, Double delta, LocalDateTime createdOn) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.delta = delta;
        this.createdOn = createdOn;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getDelta() {
        return delta;
    }
    
    public void setDelta(Double delta) {
        this.delta = delta;
    }
    
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
    
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    
    @Override
    public String toString() {
        return "DataItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", delta=" + delta +
                ", createdOn=" + createdOn +
                '}';
    }
}