package com.audiometer.model;

import com.audiometer.model.enums.Ear;
import com.audiometer.model.enums.Frequency;
import java.util.HashMap;
import java.util.Map;

public class TestState {
    private Frequency currentFrequency;
    private int currentDbHL;
    private Ear currentEar;
    private Map<Frequency, Threshold> results;

    public TestState(Frequency startFreq, int startDbHL, Ear startEar) {
        this.currentFrequency = startFreq;
        this.currentDbHL = startDbHL;
        this.currentEar = startEar;
        this.results = new HashMap<>();
    }

    public Frequency getCurrentFrequency() { return currentFrequency; }
    public void setCurrentFrequency(Frequency currentFrequency) { this.currentFrequency = currentFrequency; }

    public int getCurrentDbHL() { return currentDbHL; }
    public void setCurrentDbHL(int currentDbHL) { this.currentDbHL = currentDbHL; }

    public Ear getCurrentEar() { return currentEar; }
    public void setCurrentEar(Ear currentEar) { this.currentEar = currentEar; }

    public Map<Frequency, Threshold> getResults() { return results; }
}
