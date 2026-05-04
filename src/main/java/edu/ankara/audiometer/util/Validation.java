package edu.ankara.audiometer.util;

public final class Validation {
    private Validation() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidAge(int age) {
        return age > 0 && age <= 120;
    }
}
