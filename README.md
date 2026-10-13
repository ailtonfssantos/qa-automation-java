# QA Automation – Lobos Quad Rugby

Automated testing project for the [Lobos Quad Rugby](https://lobos-quad-rugby.vercel.app/) website, focused on validating the membership registration form.

## Project Overview

This project automates functional tests for the **Join Us** form, helping verify that form fields, mandatory information, privacy consent, and HTML5 validation behave as expected.

The project follows the **Page Object Model (POM)** design pattern to improve code organization, maintainability, and test reusability.

## Technologies

- Java 17
- Selenium WebDriver 4
- JUnit 5
- Maven
- Allure Reports
- Chrome WebDriver

## Automated Tests

The project includes 9 automated test cases covering:

- Page and form loading
- Required form fields
- Empty form validation
- Invalid email format
- Required field data entry
- Valid form data and privacy consent
- Submit button visibility and enabled state
- Missing privacy consent
- Missing phone number

## Key Features

- Page Object Model (POM)
- Automated browser testing with Selenium
- Test execution and assertions with JUnit 5
- Detailed test reports using Allure
- Automatic screenshot capture when a test fails
- Maven-based test execution

## Prerequisites

Make sure you have the following installed:

- JDK 17 or later
- Apache Maven
- Google Chrome

## How to Run the Tests

Clone or download this repository and navigate to the project directory.

Run the automated tests:

```bash
mvn clean test
```

## Generate the Allure Report

After running the tests, execute:

```bash
mvn allure:serve
```

This command generates and opens the Allure report in your browser.

The report includes test results and, when a test fails, the screenshot captured by the failure-handling extension.

## Project Structure

```text
qa-automation-java/
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       │   └── es/
│       │       └── lobosquadrugby/
│       │           ├── UnetePage.java
│       │           ├── UnetePageTest.java
│       │           └── ScreenshotOnFailureExtension.java
│       └── resources/
│           └── allure.properties
├── pom.xml
└── README.md
```

## Purpose

This project is part of my QA Automation portfolio, demonstrating practical skills in Java, Selenium WebDriver, JUnit 5, automated functional testing, Page Object Model, and test reporting.

---

**Application under test:** [Lobos Quad Rugby – Join Us](https://lobos-quad-rugby.vercel.app/unete)