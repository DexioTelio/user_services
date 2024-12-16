CREATE TYPE gender AS ENUM ('male', 'female', 'other');
CREATE TYPE account_status AS ENUM ('active', 'inactive', 'suspended', 'pending', 'closed', 'banned');

CREATE TABLE persons (
    person_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_birth DATE NOT NULL CHECK (date_birth <= CURRENT_DATE - INTERVAL '18 years'),
    email VARCHAR(100) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    gender gender,
    profile_image_url VARCHAR(255),
    last_login TIMESTAMP NOT NULL,
    account_non_expired BOOLEAN DEFAULT TRUE,
    credential_is_non_expired BOOLEAN DEFAULT TRUE,
    account_non_locked BOOLEAN DEFAULT TRUE,
    enable BOOLEAN DEFAULT TRUE,
    communication_preference VARCHAR(20),
    terms_accepted BOOLEAN DEFAULT FALSE NOT NULL,
    account_status account_status DEFAULT 'pending',
    email_verified BOOLEAN DEFAULT FALSE,
    bio TEXT,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX idx_email ON persons(email);
CREATE INDEX idx_date_birth ON persons(date_birth);
CREATE INDEX idx_gender ON persons(gender);
CREATE INDEX idx_account_status ON persons(account_status);
CREATE INDEX idx_created_at ON persons(created_at);
