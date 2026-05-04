# Audiometer System

## Project Overview

This repository contains the Maven-based Java clinical software for the multidisciplinary audiometer project described in `2526_MultidisiplinaryTermProjectDetails_V03.pdf`. The application provides the audiologist-facing JavaFX desktop UI, sends commands over serial communication, receives `RESPONSE` style messages, and hosts the software boundary for Hughson-Westlake based hearing tests.

## System Architecture

The PDF defines a four-team project, but the technical runtime has two major components:

1. Virtual hardware built in Proteus with Arduino UNO, COMPIM, MCP4921 DAC, and LM358 buffer stages.
2. Clinical desktop software built in Java/JavaFX and organized as a Maven project.

Within this repository, the Java side is separated into GUI, serial communication, audiometry logic, model, utility, and resource layers so that hardware access and medical logic remain isolated from the UI. Team-level ownership is documented in:

- [docs/software-architecture.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/software-architecture.md)
- [docs/development-workflow.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/development-workflow.md)
- [docs/teams/computer-engineering.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/teams/computer-engineering.md)
- [docs/teams/software-engineering.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/teams/software-engineering.md)
- [docs/teams/biomedical-engineering.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/teams/biomedical-engineering.md)
- [docs/teams/electrical-electronics-engineering.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/teams/electrical-electronics-engineering.md)

## Repository Structure

```text
audiometer-system/
├── docs/
│   ├── integration-protocol.md
│   ├── development-workflow.md
│   ├── software-architecture.md
│   ├── teams/
│   └── test-plan.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/ankara/audiometer/
│   │   │       ├── app/
│   │   │       ├── audiometry/
│   │   │       ├── gui/
│   │   │       ├── i18n/
│   │   │       ├── model/
│   │   │       ├── serial/
│   │   │       └── util/
│   │   └── resources/
│   │       ├── css/
│   │       ├── fxml/
│   │       └── i18n/
│   └── test/
│       └── java/
│           └── edu/ankara/audiometer/
├── pom.xml
└── .gitignore
```

## Requirements

- JDK 21 or newer
- Maven 3.9 or newer
- A serial endpoint or Proteus/Arduino setup for non-stub integration

## How to Build

```bash
mvn clean compile
```

## How to Run

```bash
mvn javafx:run
```

## Team Responsibilities

- Computer engineering: JavaFX GUI, frequency/intensity controls, serial command transmission, response reading, and audiogram presentation.
- Software engineering: Hughson-Westlake flow, pure function style medical calculations, immutable data structures where practical, safe parsing and error handling, and automated tests.
- Biomedical engineering: Hughson-Westlake rule definition, test frequency range, IEC 60645-1 interpretation, and clinical validation criteria.
- Electrical-electronics engineering: Proteus circuit, DAC tone generation, virtual or optional physical response button, and serial protocol compatibility with Java.
- Integration boundary: raw serial messages are parsed in `serial`, then converted into domain-level patient responses inside `audiometry`.

## Testing

Run the automated test suite with:

```bash
mvn clean test
```

Current tests cover stub serial behavior, serial response parsing, basic Hughson-Westlake step behavior, and separation of left/right ear threshold data.

## Serial Communication Protocol

The Java application is structured around line-oriented command/response messages such as:

```text
PLAY;FREQ=1000;DB=40;EAR=RIGHT
RESPONSE
NO_RESPONSE
ERROR;CODE=PORT_BUSY
```

See [docs/integration-protocol.md](/Users/yusufdemir/audiometer-workspace/audiometer-system/docs/integration-protocol.md) for protocol details.
