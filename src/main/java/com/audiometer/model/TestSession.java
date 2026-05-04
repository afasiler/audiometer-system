package com.audiometer.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSession {
    private Patient patient;
    private List<Threshold> thresholds;
    private LocalDateTime startTime;

    public TestSession(Patient patient) {
        this.patient = patient;
        this.thresholds = new ArrayList<>();
        this.startTime = LocalDateTime.now();
    }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    
    public List<Threshold> getThresholds() { return thresholds; }
    public void addThreshold(Threshold threshold) { this.thresholds.add(threshold); }
    
    public LocalDateTime getStartTime() { return startTime; }
}
