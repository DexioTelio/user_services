CREATE TYPE gender AS ENUM ('male', 'female', 'other');
CREATE TYPE role AS ENUM ('client', 'admin', 'manager', 'guest', 'customer support');
CREATE TYPE account_status AS ENUM ('active', 'inactive', 'suspended', 'pending', 'closed', 'banned')

CREATE TABLE person (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_birth TIMESTAMP NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    gender gender,
    role role,
    profile_image_url VARCHAR(255),
    last_login TIMESTAMP NOT NULL,
    communication_preference VARCHAR(20),
    terms_accepted BOOLEAN DEFAULT FALSE,
    account_status account_status DEFAULT 'active',
    email_verified BOOLEAN DEFAULT FALSE,
    bio TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE INDEX idx_email ON person(email);
CREATE INDEX idx_date_birth ON person(date_birth);
CREATE INDEX idx_gender ON person(gender);
CREATE INDEX idx_account_status ON person(account_status);
CREATE INDEX idx_created_at ON person(created_at);