
CREATE TABLE users
(
    id           UUID NOT NULL,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    username     VARCHAR(255),
    password     VARCHAR(255),
    email        VARCHAR(255),
    phone_number VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);