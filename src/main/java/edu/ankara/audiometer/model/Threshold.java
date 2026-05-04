package edu.ankara.audiometer.model;

import edu.ankara.audiometer.model.enums.Ear;
import edu.ankara.audiometer.model.enums.Frequency;

public record Threshold(Frequency frequency, int dbHL, Ear ear) {
}
