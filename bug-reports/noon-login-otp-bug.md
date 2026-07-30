# BUG-01: OTP Not Invalidated After Resend — Allows Login With Old Code

## Description
After requesting a new OTP via Resend, the previously issued OTP should be invalidated. Entering the old OTP still completes login successfully instead of showing an invalid-code error.

## Preconditions
- Valid Noon user account exists
- Tester has access to receive OTP codes (email/SMS) for the test account

## Steps to Reproduce
1. Click Login and enter a valid email.
2. Tap Continue to reach the OTP screen.
3. Tap Resend OTP (wait until the new OTP flow completes).
4. Enter the previous OTP (the one from before Resend), not the newest code.
5. Submit login / verify.

## Expected Result
Login is rejected; user sees an error such as "Invalid code" (or equivalent); user is not authenticated.

## Actual Result
User logs in successfully using the old OTP after Resend.

## Severity
High (authentication / OTP lifecycle)

## Priority
High — affects core authentication security; should be fixed before other lower-impact issues

## Reproducibility
Always (reproduced consistently across repeated attempts)

## Impact / Risk
Defeats the purpose of the Resend mechanism. If an old OTP is exposed or intercepted (e.g. via a compromised email/SMS channel, shoulder-surfing, or delayed delivery to the wrong recipient), it remains usable for login indefinitely instead of expiring once a new code is requested. This weakens the OTP flow as an authentication control.

## Environment
- URL: https://noon.com
- Browser: Chrome
- OS: Windows 11
- Date/time tested: May 9th, 2026

## Evidence
Screen recording of steps: https://github.com/user-attachments/assets/ab180bb0-e1c6-4026-b726-56bfeb986ef9
