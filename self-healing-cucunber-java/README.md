# healenium-cucumber

UI automation framework using Selenium + Cucumber with optional Healenium self-healing support.

## Tech Stack
- Java 17
- Selenium 4
- Cucumber 7 with JUnit 5
- Maven
- Healenium (`healenium-web`) for optional self-healing

## Project Workflow
1. Cucumber runs features from `src/test/resources/feature`.
2. `Hooks` loads `src/test/resources/config.properties`.
3. `DriverFactory` creates driver by `browser` and `selfHealing` values.
4. Step definitions in `SampleAppStepDefinition` run against `sample_app/Dynamic_Page.html`.

## Driver Creation Logic
`src/test/resources/config.properties`:

```properties
browser = chrome
selfHealing=false
```

- When `selfHealing=true`, `DriverFactory` wraps the browser delegate with `SelfHealingDriver`.
- When `selfHealing=false`, `DriverFactory` returns normal Selenium `WebDriver`.

## Current UI and Test Flow
The sample form includes:
- `Initial` dropdown with options `Miss`, `Mr.`, `Mrs.`.
- `First Name` and `Last Name` in the same row as `Initial`.
- `Recieve company notification` checkbox after `Pin Code`.
- Checkbox, `Cancel`, and `Confirm` on one line.

Feature flow (`sampleApp.feature`) now includes:
- selecting initial as `Mr.`
- selecting receive company notification checkbox

## Run Tests (Normal Selenium)
1. Set:

```properties
selfHealing=false
```

2. Run:

```sh
mvn clean verify
```

## Run Tests (Self-Healing Mode)
1. Set:

```properties
selfHealing=true
```

2. Start Healenium services:

```sh
docker compose -f healenium/docker-compose-web.yaml up -d
```

3. Run tests:

```sh
mvn clean verify
```

4. Open report:

`http://localhost:7878/healenium/report`

5. Stop services when done:

```sh
docker compose -f healenium/docker-compose-web.yaml down
```

## Key Files
- Runner: `src/test/java/com/iris/automation/test/RunCucumberTest.java`
- Hooks: `src/test/java/com/iris/automation/test/Hooks.java`
- Driver factory: `src/test/java/com/iris/automation/framework/DriverFactory.java`
- Steps: `src/test/java/com/iris/automation/test/SampleAppStepDefinition.java`
- Config: `src/test/resources/config.properties`
- Feature: `src/test/resources/feature/sampleApp.feature`
- App page: `sample_app/Dynamic_Page.html`
- Healenium config: `src/test/resources/healenium.properties`

## Troubleshooting
- If Maven fails with `JAVA_HOME` error, set `JAVA_HOME` to your JDK 17 path.
- For self-healing, ensure Healenium services are up and ports `7878` and `8000` are reachable.

## Contributing
Contributions, bug reports, and improvements are welcome. Open a PR with a clear description and tests where applicable.

## License
MIT License

## Author
Automation Practice- Quality Engineering @ IRIS Software
