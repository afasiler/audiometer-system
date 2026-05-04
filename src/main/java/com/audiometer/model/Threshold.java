package com.audiometer.model;

import com.audiometer.model.enums.Ear;
import com.audiometer.model.enums.Frequency;

public record Threshold(Frequency frequency, int dbHL, Ear ear) {
}
