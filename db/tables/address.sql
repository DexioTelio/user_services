-- Creación de la tabla "Address"
CREATE TABLE "Address" (
    person_id BIGINT NOT NULL REFERENCES "Users" (id) ON DELETE CASCADE,
    street VARCHAR(255) NOT NULL,
    street_number VARCHAR(20) NOT NULL,
    apartment_number VARCHAR(10),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT unique_address UNIQUE (street, street_number, city, state, postal_code, country)
);

CREATE INDEX idx_address_city ON "Address" USING btree (city);
CREATE INDEX idx_address_state ON "Address" USING btree (state);
CREATE INDEX idx_address_country ON "Address" USING btree (country);
CREATE INDEX idx_address ON "Address" USING btree
    (street, street_number, neighborhood, city, state, postal_code, country);