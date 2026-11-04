CREATE TABLE trips (
    id BIGSERIAL PRIMARY KEY,
    train_id BIGINT NOT NULL REFERENCES trains(id),
    departure_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED' CHECK (status IN ('SCHEDULED', 'DEPARTED', 'CANCELLED')),
    UNIQUE (train_id, departure_date)
);

CREATE TABLE trip_stops (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL REFERENCES trips(id) ON DELETE CASCADE,
    station_id BIGINT NOT NULL REFERENCES stations(id),
    stop_order INT NOT NULL CHECK (stop_order >= 0),
    arrival_time TIMESTAMPTZ,
    departure_time TIMESTAMPTZ,
    price_from_start NUMERIC(10,2) NOT NULL DEFAULT 0 CHECK (price_from_start >= 0),
    UNIQUE (trip_id, stop_order),
    UNIQUE (trip_id, station_id)
);

CREATE INDEX idx_trips_date_status ON trips(departure_date, status);
CREATE INDEX idx_stops_station_trip ON trip_stops(station_id, trip_id);