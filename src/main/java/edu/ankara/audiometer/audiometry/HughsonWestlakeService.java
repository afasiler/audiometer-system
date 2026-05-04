package edu.ankara.audiometer.audiometry;

import edu.ankara.audiometer.model.TestState;
import edu.ankara.audiometer.model.TestStep;
import edu.ankara.audiometer.model.Threshold;
import edu.ankara.audiometer.model.enums.Frequency;
import java.util.Map;

public interface HughsonWestlakeService {
    TestStep nextStep(TestState currentState, boolean responseReceived);
    boolean isTestComplete(TestState state);
    Map<Frequency, Threshold> finalizeThresholds(TestState state);
}
