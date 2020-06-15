# Coinmarketcap assignment

  - Created using Java Serenity BDD framework for UI and Java RestAsseured for API tasks
  - Lean Page Object Model with action classes is implemented enabling framework maintainabiity and execution performance.
  - This framework will log both the API request and response in the report which makes easy to analyse test failures

## Prerequisites
 - Maven version 3 or above and and JDK version 8 or above are already installed on the host machine.

 ## Limitations

 - Tests currently are supported on Windows/Mac environments only.
 - Browser support is currently limited to Chrome and Firefox, but can easily be extended to Safari and other supported browsers.
 - Framework design, readability and documentation areas are given higher priority over wider test coverage due to time constraints and vast scope of e-commerce domain.

## Executing the tests

Ensure you are in the project root directory and run the following command from the command line.

To execute all the API and UI tests
```
$ mvn clean verify
```

To execute all the API tests
```
$ mvn clean verify -Dcucumber.options="--tags @apiTask"
```

To execute all the UI tests
```
$ mvn clean verify -Dcucumber.options="--tags @uiTask"
```

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```
$  mvn clean verify -Dwebdriver.driver=firefox
```

Screenshots are by default enabled only for test failures to improve execution speed, to enable screenshots for each step during the tests, the following command line parameter can be passed.
```
$ mvn clean verify -Dserenity.take.screenshots=FOR_EACH_ACTION
```

Once executed the test results will be stored in this path `target/site/serenity/index.html`.

Run the following command from root directory to view the result after execution.
```
$ open target/site/serenity/index.html
```
### Environment-specific configurations and other default config
Application's environments are configured in `test/resources/serenity.conf` file, so that the tests can be run in different environments.

### Other libraries used in the framework
- Lombok - To reduce boilerplate code in Java classes and increase readabilty
- Assertj - To assert the tests. Soft assertions are used where multiple assertions are present so that user can see all passing assertions along with the failed ones.
- HamCrest - For other assertions

### Further enchancements that can easily be implemented with minimal updates
- Tests can be dockerised and can be executed on any machine without the worry of dependencies or complex setup requirements.
- `docker-compose` can be used to set-up selenium grid which enables parallel execution across multiple browsers irrespective of host OS.
- Minimal configuration is needed to execute tests on on demand test execution platforms like sauce-labs, crossbrowser testing etc.
- Currently only supporting Windows/Mac OSs with Chrome/FIrefox browsers, but Linux and other supported browsers can be added.
