# Restful-Booker API Test Suite

![API Tests](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml/badge.svg)

Automated API test suite for the [Restful-Booker](https://restful-booker.herokuapp.com/apidoc/index.html) practice API, covering authentication, CRUD operations on bookings, and negative/edge-case scenarios.

## Tools
- **Postman** — test design and manual execution
- **Newman** — CLI test runner
- **GitHub Actions** — CI, runs the full suite on every push

## Coverage
16 test cases across:
- Health check (`/ping`)
- Authentication (`/auth`) — valid and invalid login
- Booking CRUD (`/booking`) — create, read, update, partial update, delete
- Auth enforcement on all protected endpoints (no-token → 403)
- Undocumented API behavior discovered and verified through testing

Full test case documentation: [TEST_CASES.md](./TEST_CASES.md)

## Run locally
```bash
npm install -g newman
newman run postman/Restful-Booker-API-Tests.postman_collection.json --env-var "baseUrl=https://restful-booker.herokuapp.com"
```

## CI
Tests run automatically on every push via GitHub Actions (`.github/workflows/api-tests.yml`).
