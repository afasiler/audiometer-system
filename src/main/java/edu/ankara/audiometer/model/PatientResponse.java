package edu.ankara.audiometer.model;

import edu.ankara.audiometer.model.enums.Ear;
import edu.ankara.audiometer.model.enums.Frequency;

public record PatientResponse(
        Ear ear,
        Frequency frequency,
        int decibelLevel,
        boolean heard
) {
}
