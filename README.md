# QA Automation - Lobos Quad Rugby

Automated testing project for the [Lobos Quad Rugby website](https://lobos-quad-rugby.vercel.app/), focused on validating the membership registration form.

## Project Overview

This project automates functional tests for the Join Us form. It validates required fields, email format, privacy consent, and HTML5 form validation.

The project follows the Page Object Model (POM) design pattern to improve code organization, maintainability, and reusability.

## Technologies

- Java 17
- Selenium WebDriver 4.33.0
- JUnit 5.12.2
- Apache Maven
- Allure Reports
- Google Chrome

## Automated Tests

The project includes 9 automated test cases covering:

1. Page loading and form availability
2. Required form fields
3. Empty form validation
4. Invalid email format
5. Required field data entry
6. Valid form data and privacy consent
7. Submit button visibility and enabled state
8. Missing privacy consent
9. Missing phone number

## Key Features

- Page Object Model (POM)
- Browser automation with Selenium WebDriver
- Test execution and assertions with JUnit 5
- Allure test reporting
- Automatic screenshot capture when a test fails
- Maven-based test execution

## Prerequisites

Install the following tools:

- JDK 17 or later
- Apache Maven
- Google Chrome

## How to Run the Tests

Run the tests from the project root directory:

```bash
mvn clean test
```

## Generate the Allure Report

After executing the tests, run:

```bash
mvn allure:serve
```

This command generates and opens the Allure report in your browser.

The report includes test results and screenshots attached when a test fails.

## Project Structure

```text
qa-automation-java/
|-- .gitignore
|-- pom.xml
|-- README.md
|-- src/
|   |-- test/
|       |-- java/
|       |   |-- es/
|       |       |-- lobosquadrugby/
|       |           |-- UnetePage.java
|       |           |-- UnetePageTest.java
|       |           |-- ScreenshotOnFailureExtension.java
|       |-- resources/
|           |-- allure.properties
```

## Purpose

This project is part of my QA Automation portfolio and demonstrates practical skills in Java, Selenium WebDriver, JUnit 5, functional test automation, Page Object Model, and test reporting.

## Application Under Test

[Lobos Quad Rugby - Join Us](https://lobos-quad-rugby.vercel.app/unete)