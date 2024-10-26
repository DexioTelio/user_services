CREATE OR REPLACE FUNCTION check_address_before_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM address WHERE person_id = NEW.person_id) = 0 THEN
        RAISE EXCEPTION 'The user must have at least address.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;