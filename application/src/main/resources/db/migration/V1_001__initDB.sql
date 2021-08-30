CREATE SCHEMA IF NOT EXISTS lotto;

CREATE SEQUENCE hibernate_sequence;

CREATE TABLE IF NOT EXISTS draw (
    id bigint NOT NULL PRIMARY KEY,
    creation_date timestamp NOT NULL DEFAULT NOW(),
    numbers integer[] NOT NULL
);

CREATE TABLE IF NOT EXISTS bet (
    id bigint NOT NULL PRIMARY KEY,
    creation_date timestamp NOT NULL DEFAULT NOW(),
    draw_id bigint,
    CONSTRAINT fk_bet_draw_id FOREIGN KEY(draw_id) REFERENCES draw(id)
);

CREATE TABLE IF NOT EXISTS coupon (
    id bigint NOT NULL PRIMARY KEY,
    creation_date timestamp NOT NULL DEFAULT NOW(),
    bet_id bigint NOT NULL,
    numbers integer[] NOT NULL,
    CONSTRAINT fk_coupon_bet_id FOREIGN KEY(bet_id) REFERENCES bet(id)
);