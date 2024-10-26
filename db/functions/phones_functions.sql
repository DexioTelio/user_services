CREATE OR REPLACE FUNCTION check_phone_before_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM phones WHERE person_id = NEW.person_id) = 0 THEN
        RAISE EXCEPTION 'The user must have at least one telephone number.';
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_phone_format(phone_number TEXT)
RETURNS BOOLEAN AS $$
BEGIN
    RETURN phone_number ~ '^\\+?(\\d{1,3})?[-.\\s]?(\\(?\\d{1,4}?\\)?)[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$';
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_phone_uniqueness(new_phone_number TEXT, person_id BIGINT)
RETURNS BOOLEAN AS $$
BEGIN
    RETURN NOT EXISTS (
        SELECT 1
        FROM phones
        WHERE phone_number = new_phone_number AND (person_id IS NULL OR phones.person_id != person_id)
    );
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_phone()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT validate_phone_format(NEW.phone_number) THEN
        RAISE EXCEPTION 'Invalid phone number format.';
    END IF;

    IF NOT validate_phone_uniqueness(NEW.phone_number, NEW.person_id) THEN
        RAISE EXCEPTION 'Phone number already in use.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;