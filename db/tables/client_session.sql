CREATE TYPE device_type AS ENUM ('mobile', 'desktop', 'tablet', 'other');

CREATE TABLE client_session (
    session_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES clients(client_id) ON DELETE CASCADE,
    token VARCHAR(512) NOT NULL,
    refresh_token VARCHAR(512) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    ip_address INET,
    client_agent VARCHAR(520),
    device_type device_type,
    expiration TIMESTAMPTZ DEFAULT NOW() + INTERVAL '1 hour',
    created_by BIGINT REFERENCES clients(client_id),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    last_accessed TIMESTAMPTZ DEFAULT NOW(),
    two_factor_enabled BOOLEAN DEFAULT FALSE,
    two_factor_code VARCHAR(10),
    two_factor_expires TIMESTAMPTZ,
    CONSTRAINT unique_session UNIQUE (client_id, token)
);

CREATE INDEX idx_client_id ON client_session USING btree (client_id);
CREATE INDEX idx_token ON client_session USING btree (token);
CREATE INDEX idx_expiration ON client_session USING btree (expiration);
CREATE INDEX idx_last_accessed ON client_session USING btree (last_accessed);
CREATE INDEX idx_client_last_accessed ON client_session USING btree (client_id, last_accessed);
