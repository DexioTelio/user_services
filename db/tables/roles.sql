CREATE TABLE roles (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
)

INSERT INTO roles (role_name, description) VALUES
('admin', 'Administrator with full access'),
('client', 'Regular client with limited access'),
('employee', 'Employee with access to internal resources'),
('manager', 'Manager with higher privileges'),
('guest', 'Guest with read-only access');
