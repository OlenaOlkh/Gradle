# Learn Board Test1

An automation testing project using Gradle, divided into two modules:

- `test-api` — API tests (RestAssured, TestNG, etc.)  
- `test-ui` — UI tests (Selenide, TestNG, Page Object)

---

## Project Structure

```
learn-board-test1/
├── test-api/              # API tests module
│   ├── src/
│   │   ├── main/java/     # Main source code (if any)
│   │   └── test/java/     # API tests
│   └── build.gradle       # Gradle build script for test-api
├── test-ui/               # UI tests module
│   ├── src/
│   │   ├── main/java/     # Main source code (if any)
│   │   └── test/java/     # UI tests
│   └── build.gradle       # Gradle build script for test-ui
├── build.gradle           # Parent Gradle build script
├── settings.gradle        # Multi-module project settings
└── README.md              # Project documentation
```

---

## How to Run Tests

### Run API tests

```bash
./gradlew :test-api:clean :test-api:test
```

### Run UI tests

```bash
./gradlew :test-ui:clean :test-ui:test
```

### Run all tests (both modules)

```bash
./gradlew clean build
```

---

## Reports

- API test reports: `test-api/build/reports/tests/test/index.html`  
- UI test reports: `test-ui/build/reports/tests/test/index.html`

---

## Dependencies

All dependencies are specified in each module’s `build.gradle` file.

---

## Author

**Olena Olkhovska**  
QA Automation Engineer  
