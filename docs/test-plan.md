# Test Plan

The following tests should be maintained as the project evolves:

- Hughson-Westlake algorithm performs the correct dB transitions after heard and not-heard events.
- Threshold progression advances correctly when a `RESPONSE` message is received.
- Test flow behaves correctly when `NO_RESPONSE` is received.
- Invalid serial port messages are caught safely without crashing the UI.
- Right and left ear audiogram data stay separated throughout the session lifecycle.

## Current Automated Coverage

- `StubHughsonWestlakeServiceTest`
- `SerialResponseParserTest`
- `StubSerialServiceTest`
- `TestSessionTest`

## Future Coverage

- TODO: add property-based tests for threshold stepping rules.
- TODO: add integration tests around a real serial adapter when hardware protocol stabilizes.
