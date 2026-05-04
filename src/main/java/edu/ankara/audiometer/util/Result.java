package edu.ankara.audiometer.util;

import java.util.Optional;

public sealed interface Result<T> permits Result.Success, Result.Failure {
    record Success<T>(T value) implements Result<T> {
    }

    record Failure<T>(String errorMessage) implements Result<T> {
    }

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> failure(String errorMessage) {
        return new Failure<>(errorMessage);
    }

    default Optional<T> toOptional() {
        if (this instanceof Success<T> success) {
            return Optional.of(success.value());
        }
        return Optional.empty();
    }
}
