package edu.ankara.audiometer.serial;

import edu.ankara.audiometer.util.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerialResponseParserTest {

    private final SerialResponseParser parser = new SerialResponseParser();

    @Test
    void parsesResponseMessage() {
        Result<SerialMessage> result = parser.parse("RESPONSE");

        assertTrue(result instanceof Result.Success<SerialMessage>);
        SerialMessage message = ((Result.Success<SerialMessage>) result).value();
        assertEquals(SerialMessageType.RESPONSE, message.type());
    }

    @Test
    void parsesErrorMessageAttributes() {
        Result<SerialMessage> result = parser.parse("ERROR;CODE=PORT_BUSY");

        assertTrue(result instanceof Result.Success<SerialMessage>);
        SerialMessage message = ((Result.Success<SerialMessage>) result).value();
        assertEquals(SerialMessageType.ERROR, message.type());
        assertEquals("PORT_BUSY", message.attributes().get("CODE"));
    }

    @Test
    void rejectsInvalidMessageSafely() {
        Result<SerialMessage> result = parser.parse("UNKNOWN");

        assertTrue(result instanceof Result.Failure<SerialMessage>);
        assertTrue(result.toOptional().isEmpty());
    }
}
