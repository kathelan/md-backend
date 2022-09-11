CREATE TABLE IF NOT EXISTS ROLE (
    id BIGSERIAL PRIMARY KEY ,
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    created_at timestamp,
    updated_at timestamp
);

INSERT INTO ROLE(name, description, created_at)
VALUES ('DEV', 'developer', now());

INSERT INTO ROLE(name, description, created_at)
VALUES ('PO', 'project manager', now());

INSERT INTO ROLE(name, description, created_at)
VALUES ('TEST', 'tester', now());

INSERT INTO ROLE(name, description, created_at)
VALUES ('ADMIN', 'administrator', now());