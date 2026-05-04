package edu.ankara.audiometer.audiometry.stub;

import edu.ankara.audiometer.model.TestState;
import edu.ankara.audiometer.model.TestStep;
import edu.ankara.audiometer.model.Threshold;
import edu.ankara.audiometer.model.enums.Frequency;
import edu.ankara.audiometer.audiometry.HughsonWestlakeService;

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
