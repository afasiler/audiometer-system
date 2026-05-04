package edu.ankara.audiometer.audiometry;

import edu.ankara.audiometer.model.PatientResponse;
import edu.ankara.audiometer.model.enums.Ear;
import edu.ankara.audiometer.model.enums.Frequency;
import edu.ankara.audiometer.serial.SerialMessage;
import edu.ankara.audiometer.serial.SerialMessageType;

import java.util.Optional;

public class ResponseProcessor {

    public Optional<PatientResponse> toPatientResponse(
            SerialMessage message,
            Ear ear,
            Frequency frequency,
            int decibelLevel
    ) {
        if (message.type() == SerialMessageType.ERROR) {
            return Optional.empty();
        }

        return Optional.of(new PatientResponse(
                ear,
                frequency,
                decibelLevel,
                message.type() == SerialMessageType.RESPONSE
        ));
    }
}
