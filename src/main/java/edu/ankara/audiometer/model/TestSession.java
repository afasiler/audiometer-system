package edu.ankara.audiometer.model;

import edu.ankara.audiometer.model.enums.Ear;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestSession {
    private final Patient patient;
    private final List<Threshold> thresholds;
    private final LocalDateTime startTime;

    public TestSession(Patient patient) {
        this.patient = patient;
        this.thresholds = new ArrayList<>();
        this.startTime = LocalDateTime.now();
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Threshold> getThresholds() {
        return Collections.unmodifiableList(thresholds);
    }

    public List<Threshold> getThresholdsForEar(Ear ear) {
        return thresholds.stream()
                .filter(threshold -> threshold.ear() == ear)
                .collect(Collectors.toUnmodifiableList());
    }

    public void addThreshold(Threshold threshold) {
        this.thresholds.add(threshold);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
