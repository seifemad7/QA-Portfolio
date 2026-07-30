# Noon E-commerce Login Automation Framework

> Note: Credentials are placeholder values to avoid exposing personal data. Tests demonstrate framework structure and are intended for automation design practice.

This project is a Selenium WebDriver automation testing framework built to test the login functionality of the Noon e-commerce website using real-world scenarios. The goal of this project is to demonstrate practical QA automation skills including test design, framework structure, and execution using industry tools.

## Tools
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Page Object Model (POM)**
- **DataProvider** — data-driven testing
- **Maven**

## Coverage
Automated login test scenarios, positive and negative:
- Valid login
- Invalid password
- Empty email field
- Empty password field
- Invalid email format
- Email case variations

## Framework highlights
- Page Object Model design for clean and maintainable structure
- Data-driven testing using TestNG DataProvider
- Explicit waits (WebDriverWait) for handling dynamic UI elements
- Assertions for validating login success and failure cases
- Real-world testing on e-commerce website (Noon)

## Project structure
```
src/
├── base/
├── pages/
├── tests/
```

## Run locally
1. Clone the repository
2. Open in IntelliJ IDEA or Eclipse
3. Install Maven dependencies
4. Run tests using TestNG or:
```bash
mvn test
```

## Author
Seif Emadeldin Ahmed
QA / Software Testing Enthusiast
Cairo, Egypt
