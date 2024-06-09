-- V1__create_user_and_session_tables.sql

-- Создание таблицы user
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    nickname VARCHAR(50)         NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    created  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Создание таблицы session
CREATE TABLE IF NOT EXISTS sessions
(
    id      SERIAL PRIMARY KEY,
    user_id INT          NOT NULL,
    token   VARCHAR(255) NOT NULL,
    expires TIMESTAMP    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Rollback scripts
-- Эти скрипты будут выполнены при откате миграции
-- Flyway не поддерживает автоматический rollback, поэтому откат осуществляется ручным запуском

-- Откат для таблицы session
-- DROP TABLE IF EXISTS session;

-- Откат для таблицы user
-- DROP TABLE IF EXISTS user;


