# Train Ticketing System

Java Spring Boot application for train ticket booking and route management.

## Features

### Customer Features

- Search train connections between stations
- Direct and transfer connections supported
- Book one or multiple train tickets
- Overbooking prevention
- Email confirmation after booking
- Delay notifications via email

### Administrator Features

- Create/update/delete stations
- Create/update/delete routes
- Create/update/delete trains
- Create/update/delete schedules
- Add delays to schedules
- View bookings

### Technical Features

- PostgreSQL database
- Docker support
- Flyway migrations
- UUID primary keys
- Soft delete
- Global exception handling
- Spring Security Basic Authentication
- Optimized database indexes

---

# Technologies Used

- Java 25
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Flyway
- Docker
- Lombok
<img width="862" height="923" alt="Screenshot 2026-05-10 164846" src="https://github.com/user-attachments/assets/7edbd631-dd8c-455d-bbd6-3ea898a22ad4" />

---

# Running the Application

## 1. Clone Repository

```bash
git clone <YOUR_REPOSITORY_LINK>
cd train-ticketing-siemens
```

---

## 2. Start PostgreSQL with Docker

```bash
docker compose up -d
```

---

## 3. Configure Email Environment Variables

### PowerShell

```powershell
$env:MAIL_USERNAME="your_email@gmail.com"
$env:MAIL_PASSWORD="your_gmail_app_password"
```

### IntelliJ

Add environment variables inside Run Configuration:

```text
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_gmail_app_password
```

---

## 4. Run Application

```bash
./gradlew bootRun
```

Application runs on:

```text
http://localhost:8080
```

---

# Admin Credentials

```text
Username: admin
Password: admin123
```

---

# Database Design

## Main Entities

- Station
- Route
- RouteStation
- Train
- Schedule
- Booking

---

# Soft Delete

The application uses soft delete instead of permanent deletion.

Deleted entities remain in the database with:

```text
deleted = true
```

---

# API Endpoints
<img width="1853" height="857" alt="image" src="https://github.com/user-attachments/assets/9c27249c-de99-4a6b-adcf-99d32e4ef4b2" />
<img width="972" height="870" alt="image" src="https://github.com/user-attachments/assets/2fe80315-289d-45d8-bdc8-fc3ea0fc4a25" />

# Stations

## Get all stations

```http
GET /api/stations
```

### Response

```json
[
  {
    "id": "11111111-1111-1111-1111-111111111111",
    "name": "Cluj-Napoca",
    "city": "Cluj-Napoca",
    "code": "CLJ"
  }
]
```

---

## Create station (ADMIN)

```http
POST /api/stations
```

### Request

```json
{
  "name": "Constanta",
  "city": "Constanta",
  "code": "CTA"
}
```

---

# Routes

## Get all routes

```http
GET /api/routes
```

---

# Route Stations

## Add station to route (ADMIN)

```http
POST /api/route-stations
```

### Request

```json
{
  "routeId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
  "stationId": "11111111-1111-1111-1111-111111111111",
  "stopOrder": 1,
  "arrivalOffsetMinutes": 0,
  "departureOffsetMinutes": 0
}
```

---

# Trains

## Create train (ADMIN)

```http
POST /api/trains
```

### Request

```json
{
  "trainNumber": "IR500",
  "name": "InterRegio Test",
  "capacity": 100,
  "routeId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
}
```

---

# Schedules

## Create schedule (ADMIN)

```http
POST /api/schedules
```

### Request

```json
{
  "trainId": "dddddddd-dddd-dddd-dddd-dddddddddddd",
  "departureTime": "2026-05-20T08:00:00",
  "delayMinutes": 0
}
```

---

## Add delay to schedule (ADMIN)

```http
POST /api/schedules/{id}/delay
```

### Request

```json
{
  "delayMinutes": 30
}
```

Customers with bookings on that train schedule automatically receive email notifications.

---

# Connections

## Find connections

```http
GET /api/connections?originStationId=11111111-1111-1111-1111-111111111111&destinationStationId=55555555-5555-5555-5555-555555555555
```

### Response

```json
[
  {
    "direct": true,
    "legs": [
      {
        "scheduleId": "99999999-9999-9999-9999-999999999991",
        "trainNumber": "IR101",
        "trainName": "InterRegio Cluj Bucuresti",
        "fromStation": "Cluj-Napoca",
        "toStation": "Bucuresti Nord",
        "departureTime": "2026-05-15T08:00:00",
        "arrivalTime": "2026-05-15T16:00:00"
      }
    ]
  }
]
```

---

# Bookings

## Create booking

```http
POST /api/bookings
```

### Request

```json
{
  "scheduleId": "99999999-9999-9999-9999-999999999991",
  "originStationId": "11111111-1111-1111-1111-111111111111",
  "destinationStationId": "55555555-5555-5555-5555-555555555555",
  "customerName": "John Doe",
  "customerEmail": "john@example.com",
  "numTickets": 2
}
```

### Response

```json
{
  "id": "uuid",
  "bookingReference": "BOOK-123456",
  "customerName": "John Doe",
  "customerEmail": "john@example.com",
  "numTickets": 2,
  "totalPrice": 100.00
}
```

After booking confirmation, an email is automatically sent to the customer.

---

# Error Handling

Example validation error:

```json
{
  "httpStatus": "BAD_REQUEST",
  "errorCode": "_2003_NOT_ENOUGH_SEATS",
  "message": "Not enough seats. Available seats: 3",
  "path": "/api/bookings"
}
```

---

# Seed Data

The project automatically inserts predefined:

- stations
- routes
- trains
- schedules

using Flyway migrations.
