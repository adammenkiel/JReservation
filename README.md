# JReservation
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white) ![Last Commit](https://img.shields.io/github/last-commit/adammenkiel/JWebPanel?style=for-the-badge) ![Activity](https://img.shields.io/github/commit-activity/m/adammenkiel/JWebPanel?style=for-the-badge)

# Table of contents

# Project description
Full-stack ticket reservation system with Spring-Boot backend that uses hexagonal architecture approach. Work is still in-progress. Future plan is to create simple frontend to existing backend.

# RestAPI documentation
## POST /auth/login
Description: Endpoint for user authentication, user get access to send responses in /app endpoints.
Body requires:
Returns:
Example:
## POST /auth/register
Description: Endpoint for user registeration, if format authentication rules is respected and user with that data isn't already registered, creates a new account with input data.
Body requires:
Returns:
Example:
## GET /app/offers/[page]
Description: Checks pages of offers, starts with page 0.
Body requires:
Returns:
Example:
## POST /app/pay
Description: Endpoint for pay for specific product was already reserved.
Body requires:
Returns:
Example:
## POST /app/reserve
Description: Endpoint for reserve specific product. Succesful reservation makes amount of tickets dicreases by one. User have fixed time to pay for that reservation. If reservation isn't paid, tickets amount increases by one again.
Body requires:
Returns:
Example:
## GET /app/balance
Description: Checks wallets of user.
Body requires:
Returns:
Example:

# Build and run

# Tests

# Technologies

# Contact
- Email 1: akmenkiel@gmail.com
- Email 2: publicprojectsmenkiel@gmail.com