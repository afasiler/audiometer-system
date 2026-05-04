# Computer Engineering Work Plan

## Scope

This team owns the audiologist-facing desktop application.

## Main Files

- `src/main/java/edu/ankara/audiometer/gui/MainController.java`
- `src/main/java/edu/ankara/audiometer/gui/ConnectionPanelController.java`
- `src/main/java/edu/ankara/audiometer/gui/PatientPanelController.java`
- `src/main/java/edu/ankara/audiometer/gui/TestPanelController.java`
- `src/main/java/edu/ankara/audiometer/gui/AudiogramPanelController.java`
- `src/main/resources/fxml/`
- `src/main/resources/css/`
- `src/main/java/edu/ankara/audiometer/serial/SerialService.java`

## Deliverables

- test management GUI
- frequency and intensity controls
- serial port connection UI
- response listening
- real-time audiogram rendering

## Run Mode

```bash
mvn javafx:run
```

Use stub serial service first, then integrate the real serial adapter when ready.
