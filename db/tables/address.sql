CREATE TABLE address (
    address_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id BIGINT NOT NULL REFERENCES persons(person_id) ON DELETE CASCADE,
    street VARCHAR(255) NOT NULL,
    street_number VARCHAR(20) NOT NULL,
    apartment_number VARCHAR(10),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT unique_address UNIQUE (street, street_number, city, state, postal_code, country)
);

CREATE INDEX idx_address_city ON address USING btree (city);
CREATE INDEX idx_address_state ON address USING btree (state);
CREATE INDEX idx_address_country ON address USING btree (country);
CREATE INDEX idx_address ON address USING btree
    (street, street_number, neighborhood, city, state, postal_code, country);
