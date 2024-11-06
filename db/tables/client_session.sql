CREATE TABLE client_session (
    session_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT NOT NULL REFERENCES clients(id) ON DELETE CASCADE,
    token VARCHAR(512) NOT NULL,
    refresh_token VARCHAR(512) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    ip_address INET,
    user_agent VARCHAR(520),
    device_type TEXT CHECK (device_type IN ('mobile', 'desktop', 'tablet', 'other')),
    expiration TIMESTAMP WITH TIME ZONE DEFAULT NOW() + INTERVAL '1 hour',
    created_by BIGINT REFERENCES clients(id),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    last_accessed TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    two_factor_enabled BOOLEAN DEFAULT FALSE,
    two_factor_code VARCHAR(10),
    two_factor_expires TIMESTAMP WITH TIME ZONE,
    CONSTRAINT unique_session UNIQUE (client_id, token)
);

CREATE INDEX idx_user_id ON client_session USING btree (user_id);
CREATE INDEX idx_token ON client_session USING btree (token);
CREATE INDEX idx_expiration ON client_session USING btree (expiration);
CREATE INDEX idx_last_accessed ON client_session USING btree (last_accessed);
CREATE INDEX idx_user_last_accessed ON client_session USING btree (client_id, last_accessed);
