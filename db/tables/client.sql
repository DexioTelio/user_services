CREATE TYPE payment_method AS ENUM ('credit_card', 'debit_card', 'paypal', 'bank_transfer', 'cash');
CREATE TYPE customer_segment AS ENUM ('VIP', 'frequent', 'occasional', 'new');

CREATE TABLE clients (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id BIGINT REFERENCES person(id) ON DELETE CASCADE,
    loyalty_points INT NOT NULL CHECK (loyalty_points >= 0),
    preferred_payment_method payment_method,
    customer_segment customer_segment,
    last_purchase_date TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    update_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE INDEX idx_customer_segment ON clients(customer_segment);
CREATE INDEX idx_last_purchase_date ON  clients(last_purchase_date);