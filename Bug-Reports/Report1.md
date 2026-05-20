Description
After requesting a new OTP via Resend, the previously issued OTP should be invalidated. Entering the old OTP still completes login successfully instead of showing an invalid-code error.

Steps to reproduce

Click Login and enter a valid email.
Tap Continue to reach the OTP screen.
Tap Resend OTP (wait until the new OTP flow completes).
Enter the previous OTP (the one from before Resend), not the newest code.
Submit login / verify.
Expected result
Login is rejected; user sees an error such as Invalid code (or equivalent); user is not authenticated.

Actual result
User logs in successfully using the old OTP after Resend.

Severity
High (authentication / OTP lifecycle)

Environment (fill in)

URL: https://noon.com
Browser : Chrome
OS:Windows 11
Date/time tested: may 9th 2026

Screen record of the steps :
https://github.com/user-attachments/assets/ab180bb0-e1c6-4026-b726-56bfeb986ef9
