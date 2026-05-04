package edu.ankara.audiometer.serial;

import edu.ankara.audiometer.util.Result;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class SerialResponseParser {

    public Result<SerialMessage> parse(String rawMessage) {
        if (rawMessage == null || rawMessage.isBlank()) {
            return Result.failure("Serial message is empty.");
        }

        String normalized = rawMessage.trim();
        if ("RESPONSE".equalsIgnoreCase(normalized)) {
            return Result.success(new SerialMessage(SerialMessageType.RESPONSE, Map.of()));
        }

        if ("NO_RESPONSE".equalsIgnoreCase(normalized)) {
            return Result.success(new SerialMessage(SerialMessageType.NO_RESPONSE, Map.of()));
        }

        if (normalized.toUpperCase(Locale.ROOT).startsWith("ERROR")) {
            return Result.success(new SerialMessage(SerialMessageType.ERROR, parseAttributes(normalized)));
        }

        return Result.failure("Unsupported serial message: " + normalized);
    }

    private Map<String, String> parseAttributes(String rawMessage) {
        Map<String, String> attributes = new LinkedHashMap<>();
        String[] parts = rawMessage.split(";");
        for (int i = 1; i < parts.length; i++) {
            String[] keyValue = parts[i].split("=", 2);
            if (keyValue.length == 2) {
                attributes.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return Map.copyOf(attributes);
    }
}
