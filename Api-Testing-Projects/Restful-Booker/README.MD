[![API Tests](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml/badge.svg)](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml)
# Test Cases — Restful-Booker API

API under test: `https://restful-booker.herokuapp.com`
Docs: `https://restful-booker.herokuapp.com/apidoc/index.html`

Automated in Postman, run via Newman in CI (see `.github/workflows/api-tests.yml`).

| ID | Endpoint | Title | Preconditions | Steps | Expected Result | Priority |
|----|----------|-------|----------------|-------|------------------|----------|
| TC-01 | GET /ping | Health check returns 201 | None | Send GET to `/ping` | Status 201, response time < 1s | High |
| TC-02 | POST /auth | Valid login returns token | None | POST `username=admin, password=password123` | Status 200, response contains non-empty `token` | High |
| TC-03 | POST /auth | Invalid password does not return token | None | POST `username=admin, password=wrong` | Status 200, response has no `token` field | Medium |
| TC-04 | POST /booking | Create booking with valid data | None | POST full valid payload | Status 200, `bookingid` returned, booking data matches input | High |
| TC-05 | POST /booking | Create booking missing firstname | None | POST payload without `firstname` | Document actual behavior (expected: 400, verify real result) | Medium |
| TC-06 | GET /booking/:id | Get existing booking returns correct data | Booking exists (TC-04) | GET `/booking/{id}` | Status 200, fields match created booking | High |
| TC-07 | GET /booking/:id | Get non-existent booking | None | GET `/booking/999999` | Status 404 | Medium |
| TC-08 | PUT /booking/:id | Update booking without token fails | Booking exists | PUT with no auth header | Status 403 | High |
| TC-09 | DELETE /booking/:id | Delete booking with valid token | Booking exists, token obtained | DELETE with valid token | Status 201, subsequent GET returns 404 | High |
