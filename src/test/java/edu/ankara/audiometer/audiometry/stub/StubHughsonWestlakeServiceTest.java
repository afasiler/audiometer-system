package edu.ankara.audiometer.audiometry.stub;

import edu.ankara.audiometer.model.TestState;
import edu.ankara.audiometer.model.enums.Ear;
import edu.ankara.audiometer.model.enums.Frequency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StubHughsonWestlakeServiceTest {

    private final StubHughsonWestlakeService service = new StubHughsonWestlakeService();

    @Test
    void decreasesDbWhenPatientResponds() {
        TestState state = new TestState(Frequency.HZ_1000, 40, Ear.RIGHT);

        var step = service.nextStep(state, true);

        assertFalse(step.isComplete());
        assertEquals(30, state.getCurrentDbHL());
    }

    @Test
    void increasesDbWhenPatientDoesNotRespond() {
        TestState state = new TestState(Frequency.HZ_1000, 40, Ear.RIGHT);

        var step = service.nextStep(state, false);

        assertFalse(step.isComplete());
        assertEquals(45, state.getCurrentDbHL());
    }
}
