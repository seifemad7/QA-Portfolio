[![API Tests](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml/badge.svg)](https://github.com/seifemad7/QA-Portfolio/actions/workflows/api-tests.yml)
# Test Cases — Restful-Booker API

API under test: `https://restful-booker.herokuapp.com`
Docs: `https://restful-booker.herokuapp.com/apidoc/index.html`

Automated in Postman, run via Newman locally and in CI on every push (see `.github/workflows/api-tests.yml`).

> Note: the official docs only list success responses for every endpoint. Error status codes below (403, 404, 500) were not documented — they were discovered and verified by running the actual requests.

| ID | Endpoint | Title | Preconditions | Steps | Expected Result | Actual Result | Status |
|----|----------|-------|----------------|-------|------------------|----------------|--------|
| TC-01 | GET /ping | Health check returns 201 | None | Send GET to `/ping` | Status 201 | Status 201 | PASS |
| TC-02 | POST /auth | Valid login returns token | None | POST `username=admin, password=password123` | Status 200, response contains non-empty `token` | Status 200, `token` present in response | PASS |
| TC-03 | POST /auth | Invalid password does not return token | None | POST `username=admin, password=wrong` | Status 200, `reason: "Bad credentials"`, no `token` field | Status 200, `reason: "Bad credentials"`, `token` absent | PASS |
| TC-04 | POST /booking | Create booking with valid data | None | POST full valid payload (firstname: Jim, lastname: Brown, totalprice: 111, depositpaid: true, checkin: 2018-01-01, checkout: 2019-01-01, additionalneeds: Breakfast) | Status 200, `bookingid` returned, booking data matches input exactly | Status 200, `bookingid` returned as number, all fields matched input exactly | PASS |
| TC-05 | POST /booking | Create booking missing firstname | None | POST payload without `firstname` field | Undocumented — verify via testing | Status 500 | PASS (documented finding) |
| TC-06 | GET /booking | Get all booking IDs | None | GET `/booking` | Status 200, array of `{bookingid}` objects | Status 200, response is an array, each item has `bookingid` | PASS |
| TC-07 | GET /booking/:id | Get existing booking returns correct data | Booking exists (TC-04) | GET `/booking/{bookingId}` with valid token | Status 200, `firstname`/`lastname` present, `totalprice` > 0, `depositpaid` true, `checkin` matches `CCYY-MM-DD` | Status 200, all fields present and valid, `depositpaid: true`, `checkin` matched date format | PASS |
| TC-08 | GET /booking/:id | Get non-existent booking | None | GET `/booking/3232323` | Undocumented — verify via testing | Status 404 | PASS (documented finding) |
| TC-09 | PUT /booking/:id | Update booking with valid token | Booking exists, token obtained | PUT full valid payload (totalprice: 150) with `Cookie: token=` header | Status 200, `totalprice` updated | Status 200, `totalprice` returned as 150 | PASS |
| TC-10 | PUT /booking/:id | Update booking without token fails | Booking exists | PUT with no auth header | Undocumented — verify via testing | Status 403 | PASS (documented finding) |
| TC-11 | PUT /booking/:id | Update booking with missing field | Booking exists, token obtained | PUT payload missing `totalprice` | Undocumented — verify via testing | Status 400 | PASS (documented finding) |
| TC-12 | PATCH /booking/:id | Partial update changes only sent field | Booking exists, token obtained | PATCH `{ totalprice: 121 }` only | Status 200, `totalprice` = 121 | Status 200, `totalprice` returned as 121 | PASS |
| TC-13 | PATCH /booking/:id | Partial update without token fails | Booking exists | PATCH with no auth header | Undocumented — verify via testing | Status 403 | PASS (documented finding) |
| TC-14 | PATCH /booking/:id | Partial update with unchanged field value | Booking exists, token obtained | PATCH `{ firstname: "Jim" }` (same as current value) | Status 200, `firstname` remains "Jim" | Status 200, `firstname` returned as "Jim" | PASS |
| TC-15 | DELETE /booking/:id | Delete booking with valid token | Booking exists, token obtained | DELETE with valid `Cookie: token=` header | Status 201 | Status 201 | PASS |
| TC-16 | DELETE /booking/:id | Delete booking without token fails | Booking exists | DELETE with no auth header | Undocumented — verify via testing | Status 403 | PASS (documented finding) |

## Coverage summary
- **16 test cases** across Ping, Auth, and Booking (CRUD) endpoints — 16/16 passing
- Positive + negative coverage for every state-changing endpoint (Create, Update, PartialUpdate, Delete)
- Authentication enforcement verified on all protected endpoints (no-token → 403)
- Undocumented error behavior discovered through testing and locked in as regression checks
- Fully automated via Newman, runs in CI on every push (see GitHub Actions badge in README)
