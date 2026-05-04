package edu.ankara.audiometer.model;

import edu.ankara.audiometer.model.enums.Ear;
import edu.ankara.audiometer.model.enums.Frequency;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSessionTest {

    @Test
    void keepsLeftAndRightEarThresholdsSeparate() {
        Patient patient = new Patient("Test Patient", 30, "gender.other", LocalDate.now(), "Tester");
        TestSession session = new TestSession(patient);

        session.addThreshold(new Threshold(Frequency.HZ_1000, 30, Ear.RIGHT));
        session.addThreshold(new Threshold(Frequency.HZ_2000, 25, Ear.LEFT));

        assertEquals(1, session.getThresholdsForEar(Ear.RIGHT).size());
        assertEquals(1, session.getThresholdsForEar(Ear.LEFT).size());
        assertEquals(Frequency.HZ_1000, session.getThresholdsForEar(Ear.RIGHT).getFirst().frequency());
        assertEquals(Frequency.HZ_2000, session.getThresholdsForEar(Ear.LEFT).getFirst().frequency());
    }
}
