# Integration Protocol

This document defines the software-side serial communication contract between the Java application and the Proteus/Arduino simulation or a future physical prototype.

## Command Format

Commands are ASCII text messages separated by semicolons:

```text
PLAY;FREQ=1000;DB=40;EAR=RIGHT
```

Expected fields:

- `PLAY`: tone presentation command
- `FREQ`: frequency in hertz
- `DB`: intensity in dB HL
- `EAR`: `LEFT` or `RIGHT`

## Response Format

The device side should answer using compact one-line messages:

```text
RESPONSE
NO_RESPONSE
ERROR;CODE=PORT_BUSY
```

Meaning:

- `RESPONSE`: patient heard the presented tone
- `NO_RESPONSE`: patient did not hear the tone within the allowed interval
- `ERROR;CODE=...`: hardware or communication problem

## Parsing Strategy

- Raw serial text is parsed in `serial/SerialResponseParser.java`.
- Parsed messages are converted into domain-level `PatientResponse` values in `audiometry/ResponseProcessor.java`.
- Invalid messages should fail safely and must not crash the GUI.

## Notes

- TODO: finalize timeout, retry, and line termination rules with the embedded/hardware team.
- TODO: confirm whether the firmware will echo commands for debugging.
