CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE event (
    id bigint NOT NULL,
    count BIGINT,
    external_uid BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX external_uid_idx ON event (external_uid);