package com.audiometer.service.stub;

import com.audiometer.model.TestState;
import com.audiometer.model.TestStep;
import com.audiometer.model.Threshold;
import com.audiometer.model.enums.Frequency;
import com.audiometer.service.HughsonWestlakeService;

import java.util.Map;

public class StubHughsonWestlakeService implements HughsonWestlakeService {

    @Override
    public TestStep nextStep(TestState currentState, boolean responseReceived) {
        // Simple mock algorithm
        if (responseReceived) {
            currentState.setCurrentDbHL(currentState.getCurrentDbHL() - 10);
            return new TestStep(false, "Sinyal duyuldu, şiddet 10 dB düşürüldü.");
        } else {
            currentState.setCurrentDbHL(currentState.getCurrentDbHL() + 5);
            return new TestStep(false, "Sinyal duyulmadı, şiddet 5 dB artırıldı.");
        }
    }

    @Override
    public boolean isTestComplete(TestState state) {
        // Mock condition
        return state.getResults().size() >= Frequency.values().length * 2;
    }

    @Override
    public Map<Frequency, Threshold> finalizeThresholds(TestState state) {
        return state.getResults();
    }
}
