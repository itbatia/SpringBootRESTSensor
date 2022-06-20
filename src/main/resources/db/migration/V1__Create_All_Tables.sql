create table Sensor
(
    id     INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "name" VARCHAR(100) NOT NULL UNIQUE
);
create table Measurement
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "value"    DOUBLE PRECISION NOT NULL,
    raining    BOOLEAN          NOT NULL,
    created_at TIMESTAMP        NOT NULL,
    sensor     VARCHAR(100) REFERENCES Sensor ("name")
);