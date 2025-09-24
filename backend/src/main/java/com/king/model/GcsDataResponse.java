package com.king.model;

import java.util.List;

/**
 * Wrapper class for GCS response data
 */
public class GcsDataResponse {
    
    private List<DataItem> output;
    
    public GcsDataResponse() {}
    
    public List<DataItem> getOutput() {
        return output;
    }
    
    public void setOutput(List<DataItem> output) {
        this.output = output;
    }
}