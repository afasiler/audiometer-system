# Development Workflow

This document explains how the four teams should use this repository during development.

## 1. Architectural Split

The PDF defines four teams:

- Electrical-electronics engineering
- Computer engineering
- Biomedical engineering
- Software engineering

For implementation purposes, use this split:

1. Hardware-producing teams
   - Electrical-electronics
   - Biomedical
2. Java-implementing teams
   - Computer
   - Software

The Java repo is the integration point. Hardware and biomedical outputs should be documented here even if their final artifacts also live in separate tools such as Proteus.

## 2. Who Develops Where

### Computer Engineering

- `src/main/java/edu/ankara/audiometer/gui/`
- `src/main/resources/fxml/`
- `src/main/resources/css/`
- `src/main/java/edu/ankara/audiometer/serial/`

Primary responsibilities:

- GUI flows
- frequency and dB selection
- connect/disconnect flow
- serial command sending
- raw response listening
- audiogram drawing

### Software Engineering

- `src/main/java/edu/ankara/audiometer/audiometry/`
- `src/main/java/edu/ankara/audiometer/model/`
- `src/main/java/edu/ankara/audiometer/util/`
- `src/test/java/edu/ankara/audiometer/`

Primary responsibilities:

- Hughson-Westlake rules as pure functions
- immutable test data
- safe parsing and result handling
- unit tests
- property-based tests

### Biomedical Engineering

- `docs/teams/biomedical-engineering.md`
- `docs/test-plan.md`
- `docs/software-architecture.md`

Primary responsibilities:

- define Hughson-Westlake rule set
- define valid test frequencies `250 Hz` to `8000 Hz`
- explain IEC 60645-1 constraints
- define acceptance criteria for “clean tone” and threshold logic

Note:

Biomedical engineers should not directly modify JavaFX UI unless the change affects clinical procedure requirements and is coordinated with the computer/software teams.

### Electrical-Electronics Engineering

- `docs/integration-protocol.md`
- `docs/teams/electrical-electronics-engineering.md`

Primary responsibilities:

- define what Proteus/Arduino expects over serial
- map Java commands to DAC output behavior
- document button-to-`RESPONSE` behavior
- record THD / oscilloscope / spectrum validation notes

Note:

Circuit simulation files may live outside this repo, but the protocol contract and integration notes should be written here.

## 3. Recommended Delivery Order

1. Biomedical team defines algorithm rules and pass/fail behavior.
2. Electrical-electronics team confirms serial command and response behavior.
3. Software team encodes the rules into pure logic and tests.
4. Computer team wires the tested logic into the JavaFX application.

## 4. How to Run the Project

### UI-only development

Use the stub services already present in the repo:

```bash
mvn clean compile
mvn javafx:run
```

This mode is for GUI development before Proteus integration is ready.

### Logic and test development

```bash
mvn clean test
```

This mode is mainly for software engineering validation.

### Integration with Proteus

Use the same Java application, but replace or extend the stub serial implementation with a real `jSerialComm` adapter inside `src/main/java/edu/ankara/audiometer/serial/`.

## 5. Practical File Ownership

- If the change is visual or interaction-related, start in `gui/` or `resources/fxml/`.
- If the change is protocol-related, start in `serial/` and `docs/integration-protocol.md`.
- If the change is algorithm-related, start in `audiometry/`, `model/`, and `src/test/java/`.
- If the change is clinical-rule-related, update `docs/teams/biomedical-engineering.md` first, then implement the rule in code.
