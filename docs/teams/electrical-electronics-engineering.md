# Electrical-Electronics Engineering Work Plan

## Scope

This team owns the Proteus and signal-generation side of the project.

## Main Documentation Files

- `docs/integration-protocol.md`
- `docs/software-architecture.md`

## Deliverables

- Proteus circuit with Arduino UNO, COMPIM, MCP4921 DAC, and LM358
- DAC-driven pure sine generation from Java commands
- response button behavior that emits `RESPONSE`
- optional physical button integration notes for ESP32-S3 or Tang Nano 9K
- THD and signal-quality validation notes

## Contract with the Java Repo

The Java side assumes:

- command format such as `PLAY;FREQ=1000;DB=40;EAR=RIGHT`
- response format such as `RESPONSE`, `NO_RESPONSE`, and `ERROR;CODE=...`
- virtual COM communication

If hardware-side behavior changes, update `docs/integration-protocol.md` first so the Java teams can adapt without ambiguity.
