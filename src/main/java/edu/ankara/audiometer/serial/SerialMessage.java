package edu.ankara.audiometer.serial;

import java.util.Map;

public record SerialMessage(SerialMessageType type, Map<String, String> attributes) {
}
