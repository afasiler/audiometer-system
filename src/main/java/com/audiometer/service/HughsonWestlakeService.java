package com.audiometer.service;

import com.audiometer.model.TestState;
import com.audiometer.model.TestStep;
import com.audiometer.model.Threshold;
import com.audiometer.model.enums.Frequency;
import java.util.Map;

public interface HughsonWestlakeService {
    TestStep nextStep(TestState currentState, boolean responseReceived);
    boolean isTestComplete(TestState state);
    Map<Frequency, Threshold> finalizeThresholds(TestState state);
}
