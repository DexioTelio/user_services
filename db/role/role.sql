CREATE ROLE client;
CREATE ROLE employee;
CREATE ROLE admin;
CREATE ROLE manager
CREATE ROLE guest;
CREATE ROLE customer_support;

GRANT ALL PRIVILEGES ON DATABASE user_services TO admin;
GRANT SELECT, UPDATE ON TABLE clients TO client;
GRANT SELECT, UPDATE ON TABLE persons TO client;