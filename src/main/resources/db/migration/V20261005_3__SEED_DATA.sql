-- STATIONS

INSERT INTO stations (id, name, city, code, deleted) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Cluj-Napoca', 'Cluj-Napoca', 'CLJ', false),
    ('22222222-2222-2222-2222-222222222222', 'Alba Iulia', 'Alba Iulia', 'ALB', false),
    ('33333333-3333-3333-3333-333333333333', 'Sibiu', 'Sibiu', 'SB', false),
    ('44444444-4444-4444-4444-444444444444', 'Brasov', 'Brasov', 'BV', false),
    ('55555555-5555-5555-5555-555555555555', 'Bucuresti Nord', 'Bucuresti', 'BUC', false),
    ('66666666-6666-6666-6666-666666666666', 'Oradea', 'Oradea', 'ORA', false),
    ('77777777-7777-7777-7777-777777777777', 'Arad', 'Arad', 'AR', false),
    ('88888888-8888-8888-8888-888888888888', 'Timisoara Nord', 'Timisoara', 'TM', false);


-- ROUTES

INSERT INTO routes (id, name, deleted) VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Cluj-Napoca - Bucuresti Nord', false),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Oradea - Timisoara Nord', false),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Brasov - Bucuresti Nord', false);


-- ROUTE STATIONS

-- Route 1: Cluj -> Alba -> Sibiu -> Brasov -> Bucuresti
INSERT INTO route_stations (
    id, route_id, station_id, stop_order,
    arrival_offset_minutes, departure_offset_minutes, deleted
) VALUES
    ('a1111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', 1, 0, 0, false),
    ('a2222222-2222-2222-2222-222222222222', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '22222222-2222-2222-2222-222222222222', 2, 90, 100, false),
    ('a3333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '33333333-3333-3333-3333-333333333333', 3, 180, 190, false),
    ('a4444444-4444-4444-4444-444444444444', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '44444444-4444-4444-4444-444444444444', 4, 300, 315, false),
    ('a5555555-5555-5555-5555-555555555555', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '55555555-5555-5555-5555-555555555555', 5, 480, 480, false);

-- Route 2: Oradea -> Arad -> Timisoara
INSERT INTO route_stations (
    id, route_id, station_id, stop_order,
    arrival_offset_minutes, departure_offset_minutes, deleted
) VALUES
    ('b1111111-1111-1111-1111-111111111111', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '66666666-6666-6666-6666-666666666666', 1, 0, 0, false),
    ('b2222222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '77777777-7777-7777-7777-777777777777', 2, 120, 130, false),
    ('b3333333-3333-3333-3333-333333333333', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '88888888-8888-8888-8888-888888888888', 3, 220, 220, false);

-- Route 3: Brasov -> Bucuresti
INSERT INTO route_stations (
    id, route_id, station_id, stop_order,
    arrival_offset_minutes, departure_offset_minutes, deleted
) VALUES
    ('c1111111-1111-1111-1111-111111111111', 'cccccccc-cccc-cccc-cccc-cccccccccccc', '44444444-4444-4444-4444-444444444444', 1, 0, 0, false),
    ('c2222222-2222-2222-2222-222222222222', 'cccccccc-cccc-cccc-cccc-cccccccccccc', '55555555-5555-5555-5555-555555555555', 2, 170, 170, false);


-- TRAINS

INSERT INTO trains (id, train_number, name, capacity, route_id, deleted) VALUES
     ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'IR101', 'InterRegio Cluj Bucuresti', 100, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', false),
     ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'IR202', 'InterRegio Oradea Timisoara', 80, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', false),
     ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'R303', 'Regio Brasov Bucuresti', 60, 'cccccccc-cccc-cccc-cccc-cccccccccccc', false);


-- SCHEDULES

INSERT INTO schedules (id, train_id, departure_time, delay_minutes, deleted) VALUES
     ('99999999-9999-9999-9999-999999999991', 'dddddddd-dddd-dddd-dddd-dddddddddddd', '2026-05-15 08:00:00', 0, false),
     ('99999999-9999-9999-9999-999999999992', 'dddddddd-dddd-dddd-dddd-dddddddddddd', '2026-05-15 14:00:00', 0, false),
     ('99999999-9999-9999-9999-999999999993', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', '2026-05-15 09:00:00', 0, false),
     ('99999999-9999-9999-9999-999999999994', 'ffffffff-ffff-ffff-ffff-ffffffffffff', '2026-05-15 13:30:00', 0, false);