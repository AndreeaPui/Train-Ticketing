CREATE INDEX idx_route_stations_route_station_deleted
    ON route_stations(route_id, station_id, deleted);

CREATE INDEX idx_route_stations_route_stop_order
    ON route_stations(route_id, stop_order);

CREATE INDEX idx_bookings_schedule_deleted
    ON bookings(schedule_id, deleted);

CREATE INDEX idx_schedules_train_deleted
    ON schedules(train_id, deleted);

CREATE INDEX idx_schedules_departure_time
    ON schedules(departure_time);