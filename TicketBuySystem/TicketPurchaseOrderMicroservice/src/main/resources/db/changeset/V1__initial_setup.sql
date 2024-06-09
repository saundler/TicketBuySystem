-- V1__create_station_and_order_tables.sql

-- Создание таблицы stations
CREATE TABLE IF NOT EXISTS stations
(
    id SERIAL PRIMARY KEY,
    station VARCHAR(50) NOT NULL
);

-- Создание таблицы `order`
CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    from_station_id INT NOT NULL,
    to_station_id INT NOT NULL,
    status INT NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (from_station_id) REFERENCES stations(id),
    FOREIGN KEY (to_station_id) REFERENCES stations(id)
);
