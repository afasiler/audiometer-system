# Software Architecture

This repository implements the Java side of the project defined in the PDF. The PDF organizes the work across four engineering teams, while the runtime system itself is split into a hardware side and a clinical software side.

## System View

```text
Audiologist / Test Operator
        |
        v
JavaFX GUI (Computer Engineering)
        |
        v
Application Services + Serial Layer
        |
        v
Virtual COM Port
        |
        v
Proteus / Arduino / DAC / Button Simulation (Electrical-Electronics)
```

In parallel with the executable system:

- Biomedical engineering defines the hearing-test rules, frequency boundaries, and clinical interpretation.
- Software engineering turns those rules into pure functions, immutable models, parsers, and tests.

## Package Overview

- `app`: application bootstrap and service wiring. `Main.java` is the JavaFX entry point.
- `gui`: JavaFX controllers and UI orchestration. This layer should call services, not implement medical logic.
- `serial`: serial communication abstractions, command formatting, and raw message parsing.
- `audiometry`: Hughson-Westlake related services and response-to-domain processing.
- `model`: domain data such as patient, thresholds, session state, and enums.
- `i18n`: resource bundle and runtime language switching support.
- `util`: shared helper structures such as `Result` and input validation.

## Team-to-Code Mapping

- Computer engineering works mainly in `gui`, `serial`, `src/main/resources/fxml`, and `src/main/resources/css`.
- Software engineering works mainly in `audiometry`, `model`, `util`, and `src/test/java`.
- Biomedical engineering contributes rule definitions and validation criteria under `docs/` and drives the expected behavior of `audiometry` tests.
- Electrical-electronics engineering contributes circuit-side protocol expectations under `docs/` and validates that Proteus/Arduino behavior matches `serial` command assumptions.

## Design Rules

- GUI must stay isolated from audiometry rules.
- Serial parsing must stay isolated from medical interpretation.
- Medical calculations should be pure functions.
- Immutable data is preferred for completed test facts.
- Stub implementations must remain available so the UI can run before hardware integration is complete.

## Current Gaps

- TODO: replace stub Hughson-Westlake logic with the real thresholding flow from the biomedical team.
- TODO: add a real `jSerialComm` implementation behind the serial service abstraction.
- TODO: replace the audiogram placeholder with a chart component that supports right-ear red `O` and left-ear blue `X`.
