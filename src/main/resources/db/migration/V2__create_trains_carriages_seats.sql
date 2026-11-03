CREATE TABLE trains (
    id BIGSERIAL PRIMARY KEY,
    number VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE carriages (
    id BIGSERIAL PRIMARY KEY,
    train_id BIGINT NOT NULL
        REFERENCES trains(id) ON DELETE CASCADE,
    number INT NOT NULL CHECK (number > 0),
    type VARCHAR(20) NOT NULL
        CHECK (type IN ('PLATZKART', 'COUPE', 'SV')),
    UNIQUE (train_id, number)
);

CREATE TABLE seats (
    id BIGSERIAL PRIMARY KEY,
    carriage_id BIGINT NOT NULL
        REFERENCES carriages(id) ON DELETE CASCADE,
    number INT NOT NULL CHECK (number > 0),
    UNIQUE (carriage_id, number)
);

CREATE INDEX idx_carriages_train ON carriages(train_id);

CREATE INDEX idx_seats_carriage ON seats(carriage_id);
