package edu.ankara.audiometer.model;

import java.time.LocalDate;

public record Patient(
        String fullName,
        int age,
        String gender,
        LocalDate date,
        String audiologist
) {
}
