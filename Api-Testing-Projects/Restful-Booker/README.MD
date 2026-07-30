[![API Tests](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml/badge.svg)](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml)
# Test Cases — Restful-Booker API

API under test: `https://restful-booker.herokuapp.com`
Docs: `https://restful-booker.herokuapp.com/apidoc/index.html`

Automated in Postman, run via Newman locally and in CI on every push (see `.github/workflows/api-tests.yml`).

> Note: the official docs only list success responses for every endpoint. Error status codes below (403, 404, 500) were not documented — they were discovered and verified by running the actual requests.

| ID | Endpoint | Title | Preconditions | Steps | Expected Result | Priority |
|----|----------|-------|----------------|-------|------------------|----------|
| TC-01 | GET /ping | Health check returns 201 | None | Send GET to `/ping` | Status 201 | High |
| TC-02 | POST /auth | Valid login returns token | None | POST `username=admin, password=password123` | Status 200, response contains non-empty `token` | High |
| TC-03 | POST /auth | Invalid password does not return token | None | POST `username=admin, password=wrong` | Status 200, response contains `reason: "Bad credentials"`, no `token` field | Medium |
| TC-04 | POST /booking | Create booking with valid data | None | POST full valid payload (firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds) | Status 200, `bookingid` returned, booking data matches input exactly | High |
| TC-05 | POST /booking | Create booking missing firstname | None | POST payload without `firstname` field | Status 500 (verified via testing — undocumented) | Medium |
| TC-06 | GET /booking | Get all booking IDs | None | GET `/booking` | Status 200, response is an array of objects each containing `bookingid` | High |
| TC-07 | GET /booking/:id | Get existing booking returns correct data | Booking exists (TC-04) | GET `/booking/{bookingId}` with valid token | Status 200, `firstname`/`lastname` present, `totalprice` > 0, `depositpaid` true, `checkin` date matches `CCYY-MM-DD` format | High |
| TC-08 | GET /booking/:id | Get non-existent booking | None | GET `/booking/3232323` | Status 404 (verified via testing — undocumented) | Medium |
| TC-09 | PUT /booking/:id | Update booking with valid token | Booking exists, token obtained | PUT full valid payload with `Cookie: token=` header | Status 200, `totalprice` updated to new value | High |
| TC-10 | PUT /booking/:id | Update booking without token fails | Booking exists | PUT with no auth header | Status 403 (verified via testing — undocumented) | High |
| TC-11 | PUT /booking/:id | Update booking with missing field | Booking exists, token obtained | PUT payload missing `totalprice` | Status 400 (verified via testing — undocumented) | Medium |
| TC-12 | PATCH /booking/:id | Partial update changes only sent field | Booking exists, token obtained | PATCH `{ totalprice: 121 }` only | Status 200, `totalprice` = 121 | High |
| TC-13 | PATCH /booking/:id | Partial update without token fails | Booking exists | PATCH with no auth header | Status 403 (verified via testing — undocumented) | High |
| TC-14 | PATCH /booking/:id | Partial update with unchanged field value | Booking exists, token obtained | PATCH `{ firstname: "Jim" }` (same as current value) | Status 200, `firstname` remains "Jim" | Low |
| TC-15 | DELETE /booking/:id | Delete booking with valid token | Booking exists, token obtained | DELETE with valid `Cookie: token=` header | Status 201 | High |
| TC-16 | DELETE /booking/:id | Delete booking without token fails | Booking exists | DELETE with no auth header | Status 403 (verified via testing — undocumented) | High |

## Coverage summary
- **16 test cases** across Ping, Auth, and Booking (CRUD) endpoints
- Positive + negative coverage for every state-changing endpoint (Create, Update, PartialUpdate, Delete)
- Authentication enforcement verified on all protected endpoints (no-token → 403)
- Undocumented error behavior discovered and locked in as regression checks
