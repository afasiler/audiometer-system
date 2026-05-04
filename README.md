# Audiometer GUI (JavaFX Client)

This repository contains the graphical user interface (GUI) component for the Multidisciplinary Audiometer Project.
It has been rewritten in **Java 21** and **JavaFX 21** to serve as a robust, native desktop application communicating via serial ports, while mirroring the modern aesthetics (Tailwind CSS) of the original web-based prototype.

## Architecture & Responsibilities

This project is strictly the **GUI / Computer Engineering layer** of the overall system. Other teams (Electronics, Biomedical, etc.) will manage their own repositories (e.g. firmware, DSP logic) to prevent naming collisions and enforce modularity.

The application strictly adheres to the **Model-View-Controller (MVC)** architectural pattern:
- **Models** (`com.audiometer.model.*`): Domain entities (`Patient`, `TestSession`, `Threshold`, Enums).
- **Views** (`src/main/resources/fxml/`): FXML layouts with pure declarative UI. Styled entirely via `app.css`.
- **Controllers** (`com.audiometer.ui.*`): Java logic orchestrating the FXML, routing user actions to the Service layer.
- **Services** (`com.audiometer.service.*`): Interfaces defining core logic and hardware abstraction.

## Core Features

- **Responsive & Modern UI**: Replicates Tailwind CSS aesthetics natively in JavaFX (`app.css`).
- **Full Internationalization (i18n)**: Instant TR/EN localization toggle applied via JavaFX property bindings without UI reloads.
- **Hardware Abstraction**: All hardware interaction goes through the `SerialService` interface.
- **Service Stubbing**: Contains `StubSerialService` to enable UI development and end-to-end testing (Connect -> Play -> Response) without physical hardware.

## Prerequisites

- JDK 21+
- Apache Maven (Version 3.9+ recommended)

## Build & Run

To compile and launch the application:
```bash
mvn clean compile javafx:run
```

To run unit tests (JUnit 5):
```bash
mvn test
```

## Structure Details

### 1. `I18nManager`
A singleton managing resource bundles (`messages_en.properties`, `messages_tr.properties`). It provides an observable `bundleProperty()` that all UI elements bind to, enabling real-time language switching.

### 2. `ServiceRegistry`
A global locator for service implementations. By default, it registers stub implementations (`StubSerialService`) for isolated development. In production, this can be swapped with a real `JSerialCommService`.

### 3. FXML Modularity
The UI is broken down into modular panels:
- `main.fxml` (Root BorderPane)
- `connection-panel.fxml` (Serial port configs)
- `patient-panel.fxml` (Form validation & Session init)
- `test-panel.fxml` (Frequency/Intensity grid & interaction)
- `audiogram-panel.fxml` (Placeholder for future charts integration)

## Future Work (Other Teams / Stages)

1. **JSerialComm Integration**: Implement the real `SerialService` interface to replace `StubSerialService` to communicate with the physical Audiometer PCB.
2. **Hughson-Westlake Algorithm**: Implement the `HughsonWestlakeService` to run automated testing procedures based on user responses.
3. **Audiogram Charting**: Replace the placeholder in `audiogram-panel.fxml` with a JavaFX LineChart or JFreeChart implementation.
