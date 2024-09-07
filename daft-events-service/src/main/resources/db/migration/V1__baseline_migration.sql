-- User Management Module
CREATE SCHEMA user_management;

CREATE TABLE user_management.roles
(
    role_id    SERIAL PRIMARY KEY,
    role_name  VARCHAR(50) UNIQUE NOT NULL,
    permission VARCHAR(255)
);

CREATE TABLE user_management.users
(
    user_id         SERIAL PRIMARY KEY,
    username        VARCHAR(50) UNIQUE  NOT NULL,
    email           VARCHAR(100) UNIQUE NOT NULL,
    password_hash   VARCHAR(255)        NOT NULL,
    first_name      VARCHAR(50),
    last_name       VARCHAR(50),
    document_number VARCHAR(20) UNIQUE  NOT NULL,
    phone_number    VARCHAR(20),
    address         VARCHAR(255),
    profile_url     VARCHAR(255),
    enabled         VARCHAR(255),
    non_locked      VARCHAR(255),
    using_mfa       VARCHAR(255),
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_management.user_roles
(
    user_id INTEGER REFERENCES user_management.users (user_id),
    role_id INTEGER REFERENCES user_management.roles (role_id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE user_management.user_roles
(
    user_id INTEGER REFERENCES user_management.users (user_id),
    role_id INTEGER REFERENCES user_management.roles (role_id),
    PRIMARY KEY (user_id, role_id)
);
