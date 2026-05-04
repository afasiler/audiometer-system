# Software Engineering Work Plan

## Scope

This team owns the functional core of the Java application.

## Main Files

- `src/main/java/edu/ankara/audiometer/audiometry/`
- `src/main/java/edu/ankara/audiometer/model/`
- `src/main/java/edu/ankara/audiometer/util/`
- `src/main/java/edu/ankara/audiometer/serial/SerialResponseParser.java`
- `src/test/java/edu/ankara/audiometer/`

## Deliverables

- pure Hughson-Westlake rule implementation
- immutable domain data
- `Optional` / `Result` style safe error handling
- map/filter/reduce style response processing
- unit tests and property-based tests

## Run Mode

```bash
mvn clean test
```

This team should treat GUI and serial I/O as adapters around the core logic, not as part of the core logic itself.
