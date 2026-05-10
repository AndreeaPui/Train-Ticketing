CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE stations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    code VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE routes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL
);

CREATE TABLE route_stations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    route_id UUID NOT NULL REFERENCES routes(id) ON DELETE CASCADE,
    station_id UUID NOT NULL REFERENCES stations(id),
    stop_order INT NOT NULL,
    arrival_offset_minutes INT NOT NULL,
    departure_offset_minutes INT NOT NULL
);

CREATE TABLE trains (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    train_number VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    route_id UUID NOT NULL REFERENCES routes(id)
);

CREATE TABLE schedules (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    train_id UUID NOT NULL REFERENCES trains(id),
    departure_time TIMESTAMP NOT NULL,
    delay_minutes INT NOT NULL DEFAULT 0
);

CREATE TABLE bookings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    booking_reference VARCHAR(50) NOT NULL UNIQUE,
    schedule_id UUID NOT NULL REFERENCES schedules(id),
    origin_station_id UUID NOT NULL REFERENCES stations(id),
    destination_station_id UUID NOT NULL REFERENCES stations(id),
    customer_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(150) NOT NULL,
    num_tickets INT NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);